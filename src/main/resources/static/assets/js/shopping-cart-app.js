const app = angular.module("shopping-cart-app", []);

app.controller("shopping-cart-ctrl", function($scope, $http) {
    // QUẢN LÍ GIỎ HÀNG
    // thêm giỏ hàng thành công
    showSwal = function(type) {
        'use strict';
        if (type === 'success-message') {
            swal({
                title: 'Đã thêm vào giỏ hàng của bạn!',
                type: 'success',
                button: {
                    text: "Continue",
                    value: true,
                    visible: true,
                    className: "btn btn-primary"
                }
            })
        } else {
            swal("Error occured !");
        }
    }

    // Use Voucher
    useVoucher = function(type) {
        'use strict';
        if (type === 'success-message') {
            swal({
                title: 'Đã sử dụng voucher thành công',
                type: 'success',
                button: {
                    text: "Continue",
                    value: true,
                    visible: true,
                    className: "btn btn-primary"
                }
            })
        } else {
            swal("Error occured !");
        }
    }

    $scope.coupon = function() {
        $http.get(`/rest/coupons`).then(resp => {
            $scope.coupon = resp.data;
        })
    }

    $scope.coupon();
    $scope.form = {};
    $scope.edit = function(item) {
        $scope.form = angular.copy(item)
    }

    $scope.cart = {
        items: [],
        coupons: [],
        // Thêm sp vào giỏ hàng
        add(productid) {
            // alert("Thêm vào thành công")
            var item = this.items.find(item => item.productid == productid);
            if (item) {
                item.qty++;
                this.saveToLocalStorage();
            } else {
                $http.get(`/rest/products/${productid}`).then(resp => {
                    resp.data.qty = 1;
                    this.items.push(resp.data);
                    this.saveToLocalStorage();
                })
            }

        },

        discount0() {
            this.coupons = [{
                code: "KM0",
                couponamount: "0",
                coupontype: "Giảm giá sốc",
                description: "Xịn ghê đó",
                pricedis: "0"
            }];
            this.saveToLocalStoragedis();
            // this.loadFromLocalStorage();
        },

        discount(code) {
            var cp = "KM" + code;
            var price = document.getElementById("price").value;
            // if (code) {
            this.coupons = [];
            $http.get(`/rest/coupons/${cp}`).then(resp => {
                resp.data.pricedis = price;
                this.coupons.push(resp.data);
                this.saveToLocalStoragedis();
                this.loadFromLocalStorage();
            })
        },

        remove(productid) {
            //alert(productid)
            var index = this.items.findIndex(item => item.productid == productid);
            this.items.splice(index, 1);
            this.saveToLocalStorage();
        },
        // Xóa all sp
        clear() {
            this.items = []
            this.coupons = []
            this.saveToLocalStorage();
            this.saveToLocalStoragedis();
        },
        // Tính thành tiển của 1 sp
        amt_of(item) {},
        // Tính tổng số lượng các sp trong giỏ
        get count() {
            return this.items
                .map(item => item.qty)
                .reduce((total, qty) => total += qty, 0);
        },
        // Tổng tiền các sp có trong giỏ
        get amount() {
            return this.items
                .map(item => item.qty * (item.price - item.price * item.sale / 100))
                .reduce((total, qty) => total += qty, 0);
        },

        get amountdis() {
            return this.coupons
                .map(item => item.pricedis - (item.pricedis * item.couponamount / 100))
                .reduce((total, pricedis) => total += pricedis, 0);
        },

        get dis() {
            return this.coupons
                .map(item => item.pricedis * item.couponamount / 100)
                .reduce((total, pricedis) => total += pricedis, 0);
        },

        get km() {
            return this.coupons.map(item => item.code);
        },

        // Lưu giỏ hàng vào local storage
        saveToLocalStorage() {
            var json = JSON.stringify(angular.copy(this.items));
            localStorage.setItem("cart", json);
        },

        saveToLocalStoragedis() {
            var json = JSON.stringify(angular.copy(this.coupons));
            localStorage.setItem("coupons", json);
        },

        // Đọc giỏ hàng từ LocalStorage
        loadFromLocalStorage() {
            var json = localStorage.getItem("cart");
            this.items = json ? JSON.parse(json) : [];
        },

        loadFromLocalStoragedis() {
            var json = localStorage.getItem("coupons");
            this.coupons = json ? JSON.parse(json) : [];
        }
    }
    $scope.cart.loadFromLocalStoragedis();
    $scope.cart.loadFromLocalStorage();

    //Oder
    $scope.order = {
            createDate: new Date(),
            account: { username: $("#username").text() },
            statuss: 'DANGXULY',
            method: 'Thanh toán paypal',
            get orderDetails() {
                return $scope.cart.items.map(item => {
                    return {
                        product: { productid: item.productid },
                        price: item.price - item.price * item.sale / 100,
                        quantity: item.qty
                    }
                });
            },

            // Lưu trong local
            purchase() {
                var order = angular.copy(this);
                // Thực hiện đặt hàng
                $http.post("/rest/orders", order).then(resp => {
                    // alert("Đặt hàng thành công!");
                    location.href = "/order/detail/" + resp.data.id;
                    $scope.cart.clear();
                }).catch(error => {
                    //   alert("Đặt hàng lỗi!");
                    swal({
                        title: 'Đặt hàng lỗi!',
                        button: {
                            text: "Continue",
                            value: true,
                            visible: true,
                            className: "btn btn-primary"
                        }
                    })
                    console.log(error);
                })
            }
        }
        // var codeDis = $scope.cart.coupons.map(item => item.code).toString();
        // console.log(item);
    $scope.order1 = {

        createDate: new Date(),
        account: { username: $("#username").text() },
        coupon: { code: $scope.cart.coupons.map(item => item.code).toString() },
        statuss: 'DANGXULY',
        method: 'Thanh toán trực tiếp',

        get orderDetails() {
            return $scope.cart.items.map(item => {
                return {
                    product: { productid: item.productid },
                    price: item.price - item.price * item.sale / 100,
                    quantity: item.qty
                }
            });
        },

        purchase() {
            var order = angular.copy(this);
            // Thực hiện đặt hàng
            $http.post("/rest/orders", order).then(resp => {
                // alert("Đặt hàng thành công!");
                $scope.cart.clear();
                location.href = "/order/detail/" + resp.data.id;
            }).catch(error => {
                //   alert("Đặt hàng lỗi!");
                swal({
                    title: 'Đặt hàng lỗi!',
                    button: {
                        text: "Continue",
                        value: true,
                        visible: true,
                        className: "btn btn-primary"
                    }
                })
                console.log(error);
            })
        }
    }

    // Add Favorite Product Thêm sp yêu thích
    showSwal_favo = function(type) {
        'use strict';
        if (type === 'success-message') {
            swal({
                title: 'Đã thêm sản phẩm yêu thích!',
                type: 'success',
                button: {
                    text: "Continue",
                    value: true,
                    visible: true,
                    className: "btn btn-primary"
                }
            })
        } else {
            swal("Error occured !");
        }
    }
    $scope.favo = {
        items: [],
        add(productid) {
            // alert("Sản phẩm đã lưu vào danh sách yêu thích")
            var item = this.items.find(item => item.productid == productid);
            if (item) {
                this.saveToLocalStorage();
            } else {
                $http.get(`/rest/products/${productid}`).then(resp => {
                    resp.data.qty = 1;
                    this.items.push(resp.data);
                    this.saveToLocalStorage();
                })
            }

        },

        // Xóa sp khỏi yêu thích
        remove(productid) {
            // alert(productid)
            var index = this.items.findIndex(item => item.productid == productid);
            this.items.splice(index, 1);
            this.saveToLocalStorage();
        },

        // Xóa all sp
        clear() {
            this.items = []
            this.saveToLocalStorage();
        },
        // Tính thành tiển của 1 sp
        amt_of(item) {},
        // Tính tổng số lượng các sp trong giỏ
        get count() {
            return this.items
                .map(item => item.qty)
                .reduce((total, qty) => total += qty, 0);
        },
        // Tổng tiền các sp có trong giỏ
        get amount() {
            return this.items
                .map(item => item.qty * item.price)
                .reduce((total, qty) => total += qty, 0);
        },

        // Lưu giỏ hàng vào local storage
        saveToLocalStorage() {
            var json = JSON.stringify(angular.copy(this.items));
            localStorage.setItem("favo", json);
        },
        // Đọc giỏ hàng từ LocalStorage
        loadFromLocalStorage() {
            var json = localStorage.getItem("favo");
            this.items = json ? JSON.parse(json) : [];
        }
    }
    $scope.favo.loadFromLocalStorage();

    // popup xóa tất cả sp
    dele_all = function(type) {
            'use strict';
            if (type === 'success-message') {
                swal({
                    title: 'Đã xóa tất cả sản phẩm!',
                    type: 'success',
                    button: {
                        text: "Continue",
                        value: true,
                        visible: true,
                        className: "btn btn-primary"
                    }
                })
            } else {
                swal("Error occured !");
            }
        }
        //  Xóa 1 sản phẩm
    dele_1 = function(type) {
            'use strict';
            if (type === 'success-message') {
                swal({
                    title: 'Đã xóa một sản phẩm!',
                    type: 'success',
                    button: {
                        text: "Continue",
                        value: true,
                        visible: true,
                        className: "btn btn-primary"
                    }
                })
            } else {
                swal("Error occured !");
            }
        }
        // Đặt hàng thành công
    order_success = function(type) {
        'use strict';
        if (type === 'success-message') {
            swal({
                title: 'Đặt hàng thành công!',
                type: 'success',
                button: {
                    text: "Continue",
                    value: true,
                    visible: true,
                    className: "btn btn-primary"
                }
            })
        } else {
            swal("Error occured !");
        }
    }



})
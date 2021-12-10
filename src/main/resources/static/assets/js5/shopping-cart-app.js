const app = angular.module("shopping-cart-app", []);

app.controller("shopping-cart-ctrl", function ($scope, $http) {
    // QUẢN LÍ GIỎ HÀNG
    $scope.cart = {
        items: [],
        // Thêm sp vào giỏ hàng
        add(id) {
		alert("Thêm vào thành công")
         var item = this.items.find(item => item.id == id);
            if (item) {
                item.qty++;
                this.saveToLocalStorage();
            } else {
                $http.get(`/rest/products/${id}`).then(resp => {
                    resp.data.qty = 1;
                    this.items.push(resp.data);
                    this.saveToLocalStorage();
                })
            }

        },
        // Xóa sp khỏi giỏ hàng
        remove(id) {
   	 alert(id)
            var index = this.items.findIndex(item => item.id == id);
            this.items.splice(index, 1);
            this.saveToLocalStorage();
        },
        // Xóa all sp
        clear() {
            this.items = []
            this.saveToLocalStorage();
        },
        // Tính thành tiển của 1 sp
        amt_of(item) { },
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
            localStorage.setItem("cart", json);
        },
        // Đọc giỏ hàng từ LocalStorage
        loadFromLocalStorage() {
            var json = localStorage.getItem("cart");
            this.items = json ? JSON.parse(json) : [];
        }
    }
	
    $scope.cart.loadFromLocalStorage();

	//Oder
    $scope.order = {
        createDate: new Date(),
        address: "",
        numberphone: "",
        account: { username: $("#username").text() },
        get orderDetails() {
            return $scope.cart.items.map(item => {
                return {
                    product: { productid: item.productid },
                    price: item.price,
                    quantity: item.qty
                }
            });
        },
        purchase() {
            var order = angular.copy(this);
            // Thực hiện đặt hàng

            $http.post("/rest/orders", order).then(resp => {
                alert("Đặt hàng thành công!");
                $scope.cart.clear();
                location.href = "/order/detail/" + resp.data.id;
            }).catch(error => {
                alert("Đặt hàng lỗi!");
                console.log(error);
            })
        }
    }
    
    $scope.favo = {
		items: [],
		add(id) {
		alert("Sản phẩm đã lưu vào danh sách yêu thích")
         var item = this.items.find(item => item.id == id);
            if (item) {
                this.saveToLocalStorage();
            } else {
                $http.get(`/rest/products/${id}`).then(resp => {
                    resp.data.qty = 1;
                    this.items.push(resp.data);
                    this.saveToLocalStorage();
                })
            }

        },
        
        // Xóa sp khỏi giỏ hàng
        remove(id) {
   	 alert(id)
            var index = this.items.findIndex(item => item.id == id);
            this.items.splice(index, 1);
            this.saveToLocalStorage();
        },
        
        // Xóa all sp
        clear() {
            this.items = []
            this.saveToLocalStorage();
        },
        // Tính thành tiển của 1 sp
        amt_of(item) { },
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
    
})
app.controller("product-ctrl", function ($scope, $http) {
    $scope.items = [];
    $scope.cates = [];
    $scope.form = {};

    $scope.initialize = function () {
        // load products
        $http.get("/rest/products").then(resp => {
            $scope.items = resp.data;
            $scope.items.forEach(item => {
                item.createDate = new Date(item.createDate)
            })
        });
        // load categories
        $http.get("/rest/categories").then(resp => {
            $scope.cates = resp.data;
        });

    }
    // Khởi đầu
    $scope.initialize();

    // Xóa form
    $scope.reset = function () {
        $scope.form = {
            createDate: new Date(),
            image: 'cloud-upload.jpg',
            available: true,
        }
    }
    // Show lên form
    $scope.edit = function (item) {
        $scope.form = angular.copy(item);
        $(".nav-tabs a:eq(0)").tab('show')
    }
    // Thêm sp
    $scope.create = function () {
        var item = angular.copy($scope.form);
        $http.post(`/rest/products`, item).then(resp => {
            resp.data.createDate = new Date(resp.data.createDate)
            $scope.items.push(resp.data);
            $scope.reset();
            alert("Thêm sản phẩm thành công!");
            $scope.form.image = resp.data.name;
        }).catch(error => {
            alert("Lỗi thêm sản phẩm");
            console.log("Error", error);
        });
    }
    // Cập nhật sp
    $scope.update = function () {
        var item = angular.copy($scope.form);
        $http.post(`/rest/products/${item.id}`, item).then(resp => {
            var index = $scope.items.findIndex(p => p.id == item.id);
            $scope.items[index] = item;
            alert("Cập nhật sản phẩm thành công!");
        }).catch(error => {
            alert("Lỗi cập nhật sản phẩm");
            console.log("Error", error);
        });
    }
    // Xóa sp
    $scope.delete = function (item) {
        $http.delete(`/rest/products/${item.id}`).then(resp => {
            var index = $scope.items.findIndex(p => p.id == item.id);
            $scope.items.splice(index, 1);
            $scope.reset();
            alert("Xóa sản phẩm thành công!");
        }).catch(error => {
            alert("Lỗi xóa sản phẩm");
            console.log("Error", error);
        });
    }
    // Upload hình
    $scope.imageChanged = function (files) {
        var data = new FormData();
        data.append('file', files[0]);
        $http.post('/rest/upload/images', data, {
            transformRequest: angular.identity,
            headers: { 'Content-Type': undefined }
        }).then(resp => {
            $scope.form.image = resp.data.name;
        }).catch(error => {
            alert("Lỗi upload hình ảnh");
            console.log("Error", error);
        })
    }

    $scope.pager = {
        page: 0,
        size: 10,
        get items(){
            var start = this.page*this.size;
            return $scope.items.slice(start, start + this.size);
        },
        get count(){
        return Math.ceil(1.0 * $scope.items.length / this.size);
    	},
        first(){
            this.page = 0;
        },
        prev(){
            this.page--;
            if(this.page < 0){
                this.last;
            }
        },
        next(){
        	this.page++;
       		if(this.page >= this.count){
        	this.first();}
    	}, 
        last(){
            this.page = this.count - 1;
        }
    }
});
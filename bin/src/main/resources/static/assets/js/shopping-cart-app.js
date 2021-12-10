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
app.controller("product-ctrl", function($scope, $http) {
    $scope.items = [];
    $scope.cates = [];
    $scope.form = {};

    $scope.initialize = function() {
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
    $scope.reset = function() {
        $scope.form = {
            createDate: new Date(),
            image: 'box.jpg',
            image1: 'box.jpg',
            image2: 'box.jpg',
            image3: 'box.jpg',
            status: true,
        }
    }

    $scope.reset();
    // Show lên form
    $scope.edit = function(item) {
            $scope.form = angular.copy(item);
            $(".nav-tabs a:eq(0)").tab('show')
        }
        // Thêm sp
    add_success = function(type) {
        'use strict';
        if (type === 'success-message') {
            swal({
                title: 'Thêm mới sản phẩm thành công!',
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
    $scope.create = function() {
            var item = angular.copy($scope.form);
            $http.post(`/rest/products`, item).then(resp => {
                resp.data.createDate = new Date(resp.data.createDate)
                $scope.items.push(resp.data);
                $scope.reset();


            }).catch(error => {
                swal({
                    title: 'Thêm mới sản phẩm thất bại!',
                    button: {
                        text: "Continue",
                        value: true,
                        visible: true,
                        className: "btn btn-primary"
                    }
                })
                console.log("Error", error);
            });
        }
        // Cập nhật sp
    update_success = function(type) {
        'use strict';
        if (type === 'success-message') {
            swal({
                title: 'Cập nhật thành công!',
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
    $scope.update = function() {
        var item = angular.copy($scope.form);
        $http.put(`/rest/products/${item.productid}`, item).then(resp => {
            var index = $scope.items.findIndex(p => p.productid == item.productid);
            $scope.items[index] = item;


        }).catch(error => {
            swal({
                title: 'Cập nhật thất bại!',
                button: {
                    text: "Continue",
                    value: true,
                    visible: true,
                    className: "btn btn-primary"
                }
            })
            console.log("Error", error);
        });
    }

    // xóa lên form
    $scope.delete = function(item) {
        $scope.form = angular.copy(item);
        $scope.deleteConfirm();
    }

    // Xóa sp
    dele_success = function(type) {
        'use strict';
        if (type === 'success-message') {
            swal({
                title: 'Xóa thành công!',
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
    $scope.deleteConfirm = function() {
            var item = angular.copy($scope.form);
            $http.put(`/rest/products/delete/${item.productid}`, item).then(resp => {
                var index = $scope.items.findIndex(p => p.productid == item.productid);
                $scope.items[index] = item;
                $scope.reset();
                $scope.initialize();

            }).catch(error => {
                swal({
                    title: 'Xóa thất bại!',
                    button: {
                        text: "Continue",
                        value: true,
                        visible: true,
                        className: "btn btn-primary"
                    }
                })
                console.log("Error", error);
            });
        }
        // Upload hình
    $scope.imageChanged = function(files) {
        var data = new FormData();
        data.append('file', files[0]);
        $http.post('/rest/upload/images', data, {
            transformRequest: angular.identity,
            headers: { 'Content-Type': undefined }
        }).then(resp => {
            $scope.form.image = resp.data.name;
        }).catch(error => {
            swal({
                title: 'Up load hình lỗi!',
                button: {
                    text: "Continue",
                    value: true,
                    visible: true,
                    className: "btn btn-primary"
                }
            })
            console.log("Error", error);
        })
    }
    $scope.imageChanged1 = function(files) {
        var data = new FormData();
        data.append('file', files[0]);
        $http.post('/rest/upload/images', data, {
            transformRequest: angular.identity,
            headers: { 'Content-Type': undefined }
        }).then(resp => {
            $scope.form.image1 = resp.data.name;
        }).catch(error => {
            swal({
                title: 'Up load hình lỗi!',
                button: {
                    text: "Continue",
                    value: true,
                    visible: true,
                    className: "btn btn-primary"
                }
            })
            console.log("Error", error);
        })
    }

    $scope.imageChanged2 = function(files) {
        var data = new FormData();
        data.append('file', files[0]);
        $http.post('/rest/upload/images', data, {
            transformRequest: angular.identity,
            headers: { 'Content-Type': undefined }
        }).then(resp => {
            $scope.form.image2 = resp.data.name;
        }).catch(error => {
            swal({
                title: 'Up load hình lỗi!',
                button: {
                    text: "Continue",
                    value: true,
                    visible: true,
                    className: "btn btn-primary"
                }
            })
            console.log("Error", error);
        })
    }

    $scope.imageChanged3 = function(files) {
        var data = new FormData();
        data.append('file', files[0]);
        $http.post('/rest/upload/images', data, {
            transformRequest: angular.identity,
            headers: { 'Content-Type': undefined }
        }).then(resp => {
            $scope.form.image3 = resp.data.name;
        }).catch(error => {
            swal({
                title: 'Up load hình lỗi!',
                button: {
                    text: "Continue",
                    value: true,
                    visible: true,
                    className: "btn btn-primary"
                }
            })
            console.log("Error", error);
        })
    }


    //Phân trang
    $scope.pagesizes = [5, 10, 15, 20];
    $scope.pagesize = $scope.pagesizes[0]; //jumlah baris dalam 1 halaman
    $scope.currentpage = 0; //lokasi halama saat ini
    $scope.pagenumber = Math.ceil($scope.items.length / $scope.pagesize) //jumlah total halaman

    $scope.paging = function(type) {
        if (type == 0 && $scope.currentpage > 0) {
            --$scope.currentpage;
        } else if (type == 1 && $scope.currentpage < $scope.pagenumber - 1) {
            ++$scope.currentpage;
        }
    }

    $scope.$watchCollection('items', function() {
        if ($scope.items == undefined) return;
        $scope.currentpage = 0;
        $scope.pagenumber = Math.ceil($scope.items.length / $scope.pagesize);
    });

    $scope.changeAction = function() {
        $scope.currentpage = 0;
        $scope.pagenumber = Math.ceil($scope.items.length / $scope.pagesize);
    }

    $scope.ordering = function(ordvar, by) {
        ordvar = !ordvar;
        $scope.ordstatus = ordvar;
        $scope.ord = by;
        return ordvar;

    }
});
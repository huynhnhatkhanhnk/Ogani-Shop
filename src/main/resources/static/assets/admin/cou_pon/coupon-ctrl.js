app.controller("coupon-ctrl", function($scope, $http) {
    $scope.coupon = [];
    $scope.form = {};

    $scope.initalize = function() {
        $http.get("/rest/coupons").then(resp => {
            $scope.coupon = resp.data;
        });
    }

    //NÚT LÀM MỚI
    $scope.reset = function() {
        $scope.form = {
            image: 'box.jpg',
        }
    }
    $scope.reset();

    //NÚT SỬA
    $scope.edit = function(item) {
        $scope.form = angular.copy(item)
        $(".nav-tabs a:eq(0)").tab('show');
    }

    $scope.initalize();

    //NÚT THÊM MỚI
    // Create
    add_success = function(type) {
        'use strict';
        if (type === 'success-message') {
            swal({
                title: 'Thêm mới thành công!',
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
        $http.post(`/rest/coupons`, item).then(resp => {
            $scope.coupon.push(resp.data);
            $scope.reset();
            $scope.initalize();
        }).catch(error => {
            swal({
                title: 'Thêm mới thất bại!',
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

    //NÚT CẬP NHẬT
    // Update
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
        $http.put(`/rest/coupons/${item.code}`, item).then(resp => {
            var index = $scope.coupon.findIndex(cp => cp.code == item.code);
            $scope.coupon[index] = item;
            $scope.reset();
            $scope.initalize();
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
        })
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

    //NÚT XÓA
    // Delete
    $scope.deletetabble = function(item) {
        $scope.form = angular.copy(item);
        $scope.delete();
    }

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
    $scope.delete = function(item) {
        var item = angular.copy($scope.form);
        $http.put(`/rest/coupons/delete/${item.code}`, item).then(resp => {
            var index = $scope.coupon.findIndex(cp => cp.code == item.code);
            $scope.coupon[index] = item;
            $scope.reset();
            $scope.initalize();
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
        })
    }

});
app.controller("order-ctrl", function($scope, $http) {
    $scope.items = [];
    $scope.form = {};
    $scope.details = [];


    $scope.initalize = function() {
        //load đơn hàng
        $http.get("/rest/orders").then(resp => {
            $scope.items = resp.data;
            $scope.items.forEach(item => {
                item.createdate = new Date(item.createdate)
            })
        });
        //load orderdetail
        $http.get("/rest/orderdetail").then(resp => {
            $scope.details = resp.data;
        });
    }

    //KHỜI ĐẦU
    $scope.initalize();

    //NÚT CHỈNH SỬA
    $scope.edit = function(item) {
        $scope.form = angular.copy(item);
        $(".nav-tabs a:eq(0)").tab('show');
    }

    //NÚT XÓA
    // Delete
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
        $http.delete(`/rest/orders/${item.id}`).then(resp => {
            var index = $scope.items.findIndex(dt => dt.id == item.id);
            $scope.items.splice(index, 1);
        }).catch(error => {
            // alert("Xoá thất bại");
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

    //update
    $scope.update = function() {
        var item = angular.copy($scope.form);
        $http.put(`/rest/orders/${item.id}`, item).then(resp => {
            var index = $scope.items.findIndex(od => od.id == item.id);

            $scope.items[index] = item;

            //update
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
});
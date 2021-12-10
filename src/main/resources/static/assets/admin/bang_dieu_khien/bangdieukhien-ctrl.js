app.controller("bangdieukhien-ctrl", function($scope, $http) {
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

    $scope.delete = function(item) {
        $http.delete(`/rest/orders/${item.id}`).then(resp => {
            var index = $scope.items.findIndex(dt => dt.id == item.id);
            $scope.items.splice(index, 1);
        }).catch(error => {
            alert("Xoá thất bại");
            console.log("Error", error);
        })
    }


});
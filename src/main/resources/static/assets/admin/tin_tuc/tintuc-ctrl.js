app.controller("tintuc-ctrl", function($scope, $http) {
    $scope.items = [];
    $scope.cates = [];
    $scope.form = {};

    $scope.initialize = function() {
            // load tintuc
            $http.get("/rest/blogs").then(resp => {
                $scope.items = resp.data;
                $scope.items.forEach(item => {
                    item.createDate = new Date(item.createDate)
                })
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
				}
				else{
					swal("Error occured !");
					}
					}
    $scope.create = function() {
            var item = angular.copy($scope.form);
            $http.post(`/rest/blogs`, item).then(resp => {
                resp.data.createdate = new Date(resp.data.createdate)
                $scope.items.push(resp.data);
                $scope.reset();
              //  alert("Thêm mới thành công!");
              
                $scope.form.image = resp.data.name;
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
            });
        }
        // Cập nhật sp
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
				}
				else{
					swal("Error occured !");
					}
					}
    		$scope.update = function() {
            var item = angular.copy($scope.form);
            $http.put(`/rest/blogs/${item.id}`, item).then(resp => {
                var index = $scope.items.findIndex(blg => blg.id == item.id);
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
        // Xóa sp
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
				}
				else{
					swal("Error occured !");
					}
					}
    $scope.delete = function(item) {
            $http.delete(`/rest/blogs/${item.id}`).then(resp => {
                var index = $scope.items.findIndex(blg => blg.id == item.id);
                $scope.items.splice(index, 1);
                $scope.reset();
                
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

});
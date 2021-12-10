app.controller("account-ctrl", function($scope, $http, $location) {
	$scope.roles = [];
	$scope.admins = [];
	$scope.authorities = [];
	$scope.form = {};

	$scope.initialize = function() {
		// load tất cả roles
		$http.get("/rest/roles").then(resp => {
			$scope.roles = resp.data;
		})

		// load nhân viên và admin (administrators)
		$http.get("/rest/accounts?admin=false").then(resp => {
			$scope.admins = resp.data;
		})

		// load authorities được cấp cho staff và directors
		$http.get("/rest/authorities?admin=true").then(resp => {
			$scope.authorities = resp.data;
		}).catch(error => {
			$location.path("/unauthorized");
		})
	}

	//So sánh username và role để check lên
	$scope.authority_of = function(acc, role) {
		if ($scope.authorities) {
			return $scope.authorities.find(ur => ur.account.username == acc.username && ur.role.id == role.id);
		}
	}

	//Cấp quyền
	$scope.authority_changed = function(acc, role) {
		var authority = $scope.authority_of(acc, role); //đi tìm acc, role
		if (authority) { // đã cấp quyền => thu hồi quyền (xóa)
			$scope.revoke_authority(authority);
		} else { // chưa đc cấp quyền => cấp thêm quyền mới
			authority = { account: acc, role: role };
			$scope.grant_authority(authority);
		}
	}

	// Thêm mới authority
	$scope.grant_authority = function(authority) {
		$http.post(`/rest/authorities`, authority).then(resp => {
			$scope.authorities.push(resp.data)
			alert("Cấp quyền sử dụng thành công");
		}).catch(error => {
			alert("Cấp quyền sử dụng thất bại")
			console.log("Error", error);
		})
	}

	// Xóa authority
	$scope.revoke_authority = function(authority) {
		$http.delete(`/rest/authorities/${authority.id}`).then(resp => {
			var index = $scope.authorities.findIndex(a => a.id == authority.id);
			$scope.authorities.splice(index, 1);
			alert("Thu hồi quyền sử dụng thành công");
		}).catch(error => {
			alert("Thu hồi quyền sử dụng thất bại")
			console.log("Error", error);
		})
	}

	$scope.initialize();

	// Show lên form
	$scope.edit = function(acc) {
		$scope.form = angular.copy(acc);
		$(".nav-tabs a:eq(0)").tab('show')
	}

	// Reset form
	$scope.reset = function() {
		$scope.form = {
			image: 'user.jpg',
		}
	}
	$scope.reset();

	//NÚT THÊM MỚI
	$scope.create = function() {
		var item = angular.copy($scope.form);
		$http.post(`/rest/accounts`, item).then(resp => {
			$scope.admins.push(resp.data);
			add_success = function(type) {
				'use strict';
				if (type === 'success-message') {
					swal({
						title: 'Thêm mới tài khoản thành công!',
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
			$scope.reset();
			$scope.initialize();
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

	$scope.update = function() {
		var item = angular.copy($scope.form);
		$http.put(`/rest/accounts/${item.username}`, item).then(resp => {
			var index = $scope.admins.findIndex(c => c.username == item.username);
			$scope.admins[index] = item;
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
			$scope.reset = function() {
				$scope.form = {
					image: 'box.jpg',
				}
			}
			$scope.reset({});
			$scope.initialize();
		}).catch(error => {
			update_success = function(type) {
				'use strict';
				if (type === 'success-message') {
					swal({
						title: 'Cập nhật tài khoản thành công!',
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
			// alert("Sai rồi");
			console.log("Error", error);
		})
	}

	//NÚT XÓA
	$scope.delete = function(acc) {
		$scope.form = angular.copy(acc);
		$scope.deleteacc();

	}

	$scope.imageChanged = function(files) {
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

	$scope.deleteacc = function() {
		var acc = angular.copy($scope.form);
		$http.put(`/rest/accounts/delete/${acc.username}`, acc).then(resp => {
			var index = $scope.admins.findIndex(c => c.username == acc.username);
			$scope.admins[index] = acc;
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
			$scope.reset();
			$scope.initialize();
		}).catch(error => {
			swal({
				title: 'Xóa tài khoản thất bại!',
				button: {
					text: "Continue",
					value: true,
					visible: true,
					className: "btn btn-primary"
				}
			})
			console.log("Error", error);
		})
		$scope.initialize();
	}
});
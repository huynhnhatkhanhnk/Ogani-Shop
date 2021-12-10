$(function() {
	let pageNo = 0;
	let pageSize = 3;
	
	function show(){
		$("#vaokhong #showxuong" ).each(function(product, sp) {
			if(product >= 0 && product < (pageNo + 1) * pageSize) {
				$(sp).show();			
			}else {
				$(sp).hide();
			}
		})
	}
	
	show();
	$("#showkhong").click(function() {
		pageNo+=2;
		show();
	})
})
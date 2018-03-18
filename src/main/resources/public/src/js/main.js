var pathPrefix = "/api/v1";

$(document).ready(() => {
	openProduct();
});

//Menu
function openPage(page) {
	$.get(pathPrefix + page, content => {
		$("#content").html(content);
	});
}

function openHome() {
	openPage("/pages/home.html");
}

function openProduct() {
	openPage("/pages/product/product_list.html");
}

function openNewProduct() {
	openPage("/pages/product/new_product.html");
}

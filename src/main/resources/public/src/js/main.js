var pathPrefix = "/api/v1";

$(document).ready(() => {
	openHome();
});

//Menu
function openPage(page) {
	$.get(pathPrefix + page, content => {
		$("#content").html(content);
	});
}

function openHome() {
	openPage("/home.html");
}

function openProduct() {
	openPage("/product/product_list.html");
}

//Products
function listProducts() {
	$.ajax({
		url: "/api/v1/product"
	}).then(data => {
		let html = "<div class='table-responsive'>";
		html += "<table class='table table-striped table-sm'>";
		html += "<thead><tr><th>Id</th><th>Name</th><th>Description</th></thead>";
		html += "<tbody>";
		
		data.forEach((product) => {
			html += "<td>" + product.id + "</td>";
			html += "<td>" + product.name + "</td>";
			html += "<td>" + product.description + "</td>";
			html += "</tr>";
		});
		
		html += "</tbody></table></div>";
		
		$("#product_result").append(html);
	});
}

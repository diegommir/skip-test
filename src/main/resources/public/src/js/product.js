function listProducts() {
	$.ajax({
		url: "/api/v1/product"
	}).then(data => {
		$("#product_list").html("");
		
		let html = "<div class='table-responsive'>";
		html += "<table class='table table-striped table-sm'>";
		html += "<thead><tr><th>Id</th><th>Name</th><th>Description</th><th>#</th></thead>";
		html += "<tbody>";
		
		data.forEach((product) => {
			html += "<td>" + product.id + "</td>";
			html += "<td>" + product.name + "</td>";
			html += "<td>" + product.description + "</td>";
			html += "<td>";
			html += "	<button class='btn btn-primary' onclick='updateProduct("+ product.id + ");'>Update</button>";
			html += "	<button class='btn btn-secondary' onclick='deleteProduct("+ product.id + ");'>Del</button>";
			html += "</td>";
			html += "</tr>";
		});
		
		html += "</tbody></table></div>";
		
		$("#product_list").append(html);
	});
}

function updateProduct(id) {
	if (!id) {
		return;
	}
	
	openNewProduct();
	
	$.ajax({
		url: pathPrefix + '/product/' + id,
		type: 'GET',
		contentType : 'application/json; charset=utf-8',
		dataType: 'json',
		success: data => {
			$("#id").val(data.id);
			$("#name").val(data.name);
			$("#description").val(data.description);
		}
	});
}

function deleteProduct(id) {
	if (!id) {
		return;
	}
	
	$.ajax({
		url: pathPrefix + '/product/' + id,
		type: 'DELETE',
		contentType : 'application/json; charset=utf-8',
		dataType: 'json',
		complete: (req, status) => {
			listProducts();
		}
	});
}

function saveProduct() {
	let product = {
		"id": $("#id").val(),
		"name": $("#name").val(),
		"description": $("#description").val()
	};
	
	$.ajax({
		url: pathPrefix + '/product/' + (product.id ? product.id : ""),
		type: product.id ? 'PUT' : 'POST',
		contentType : 'application/json; charset=utf-8',
		dataType: 'json',
		data: JSON.stringify(product),
		success: data => {
			clearForm();
			openProduct();
		}
	});
}

function clearForm() {
	$("#id").val("");
	$("#name").val("");
	$("#description").val("");
}

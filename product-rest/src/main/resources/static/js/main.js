//load redirectHome function on start up
window.onload = redirectHome;

function redirectHome(){
	$("#ajaxLoadedContent").load("/home");
}

function redirectAllProducts(){
	$("#ajaxLoadedContent").load("/mvc/products/allProducts");
}

function redirectProductDetails(productId){
	$("#ajaxLoadedContent").load("/mvc/products/productDetails/" + productId);
}

function redirectAddProduct(){
	$("#ajaxLoadedContent").load("/mvc/products/addProduct");
}

function redirectUpdateProduct(productId){
	$("#ajaxLoadedContent").load("/mvc/products/updateProduct/" + productId);
}
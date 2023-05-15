var productApp = Vue.createApp({
	data(){
		return {
			productId: null,
			name: null,
			category: null, 
			price: null,
			imageUrl: null,
			productList: [],
			dt: null // data table
		}
	},
	
	created(){
		
	},
	
	mounted(){ 
		this.getAllProducts(); // similar to jquery $(document).ready
	},
	
	watch: {
		productList(val){
			this.dt.destroy();
			this.$nextTick(() => {
				this.dt = $("#listingTable").DataTable();
			})
		}
	},
	
	methods: {
		getAllProducts: function(){
			axios.get("/api/products/allProducts")
			.then((response) => {
				this.dt = $("#listingTable").DataTable();
				setTimeout(() => this.productList = response.data);
			})
		},
		
		getProduct: function(productId){
			redirectProductDetails(productId);
		},
		
		getSelectedProduct: async function(productId){
			await
			axios.get("/api/products/productDetails/" + productId)
			.then((response) => {
				var productData = response.data;
				this.productId = productData.productId;
				this.name = productData.name;
				this.category = productData.category;
				this.price = productData.price;
				this.imageUrl = productData.imageUrl;
			},function(){
				alert("Failed!") // error block
			})
		},
		
		deleteProduct: async function(productId){
			if(confirm("Remove this product?")){
				await
				axios.delete("/api/products/deleteProduct/" + productId)
				.then(function(){
					redirectAllProducts();
				},function(){
					alert("Failed!");   // error block
				})
			}
		},
		
		addProduct: async function(){
			if(validateProduct()){
				if(this.productId == "" || this.productId == null){
					await
					axios.post("/api/products/storeProduct", {
						"name" : this.name,
						"category" : this.category,
						"price" : this.price,
						"imageUrl" : this.imageUrl
					})
					.then(function(){
						redirectAllProducts();
					},function(){
						alert("Failed!");
					})
				}else {
					await
					axios.put("/api/products/updateProduct/" + this.productId, {
						"name" : this.name,
						"category" : this.category,
						"price" : this.price,
						"imageUrl" : this.imageUrl
					})
					.then(function(){
						redirectAllProducts();
					},function(){
						alert("Failed!");
					})
				}
			}
		},
		
		updateProduct: function(productId){
			redirectUpdateProduct(productId);
		},
		
		loadPreviewError: function(event){
			event.target.src = "https://t3.ftcdn.net/jpg/00/36/94/26/360_F_36942622_9SUXpSuE5JlfxLFKB1jHu5Z07eVIWQ2W.jpg";
		}
	}
	
	
}).mount("#vueContent")
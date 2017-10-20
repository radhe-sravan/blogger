(function() {
	'use strict';

	angular.module('app').controller('IndexController', IndexController);

	IndexController.$inject = [ '$http' ];

	function IndexController($http) {
		var ic = this;

		ic.posts = [];
		ic.getAllPosts = getAllPosts;
		ic.goToPost = goToPost;

		init();

		function init() {
			getAllPosts();
		}

		function getAllPosts() {
			var url = "/blogger/1.0/posts";
			var postPromise = $http.get(url);
			postPromise.then(function(response) {
				ic.posts = response.data;
			});
		}

		function goToPost(id) {
			var url = "/post/" + id;
			window.location.href = url;
		}

	}
})();
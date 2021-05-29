angular.module('app', []).controller('productController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/market';
    $scope.itemsSum = 0;
    $scope.init = function () {
        $http.get(contextPath + '/api/v1/products')
            .then(function (response) {
                $scope.products = response.data;
                $scope.cartShow();

            });
    };


    $scope.createNewProduct = function () {
        console.log($scope.newProduct);
        $http.post(contextPath + '/api/v1/products', $scope.newProduct)
            .then(function successCallBack(response) {
                $scope.newProduct = null;
                $scope.init();
            }, function errorCallback(response) {
                console.log(response.data);
                alert("Error!!! \n" + response.data.messages);
            });
    };
    $scope.clickOnProduct = function (product) {
        console.log(product);
    }


    /*
    * Cart functions
    * */

    $scope.sumItems = function () {
        $http.get(contextPath + '/api/v1/cart/summ')
            .then(function (response) {
                $scope.itemsSum = response.data;
            });
    };
    $scope.cartShow = function () {
        $http.get(contextPath + '/api/v1/cart')
            .then(function (response) {
                $scope.cart = response.data;
                $scope.sumItems();
            });
    }

    $scope.addProduct = function (productId) {
        $http({
            url: contextPath + "/api/v1/cart/add",
            method: "GET",
            params: {
                id: productId
            }
        }).then(function (response) {
            $scope.cartShow();
            $scope.itemsSum = response.data;
            console.log("OK");
        })

    }

    $scope.deleteItem = function (idProduct) {
        $http({
            url: contextPath + "/api/v1/cart/delete",
            method: "GET",
            params: {
                id: idProduct
            }
        }).then(function (response) {
            $scope.itemsSum = response.data;
            $scope.cartShow();
            console.log("OK");
        })
    }

    $scope.init();
});

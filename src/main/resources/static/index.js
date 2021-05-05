angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/market';

    $scope.init = function () {
        $http.get(contextPath + '/api/v1/products')
            .then(function (response) {
                $scope.products = response.data;
            });
    };

    $scope.createNewProduct = function () {
        console.log($scope.newProduct);
        $http.post(contextPath + '/api/v1/products', $scope.newProduct)
            .then(function successCallBack (response) {
                $scope.newProduct=null;
                $scope.init();
            }, function errorCallback(response){
                console.log(response.data);
                alert("Error!!! \n" + response.data.messages);
            });
    };
    $scope.clickOnProduct=function (product) {
        console.log(product);
    }

    $scope.pingProduct=function (productId) {
        $http({
            url: contextPath + "/api/v1/cart/ping",
            method: "GET",
            params: {
                id: productId
            }
        }).then(function (response) {
            console.log("OK");
        })
    }

    $scope.init();
});
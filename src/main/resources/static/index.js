angular.module('app', []).controller('productController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/market';

    $scope.itemsSum = 0;
    $scope.loadPage = function (page) {
        $http({
            url: contextPath + "/api/v1/products",
            method: "GET",
            params: {
                p: page
            }
        }).then(function (response) {
            $scope.productsPage = response.data;


            let minPageIndex = page - 2;
            if (minPageIndex < 1) {
                minPageIndex = 1;
            }

            let maxPageIndex = page + 2;
            if (maxPageIndex > $scope.productsPage.totalPages) {
                maxPageIndex = $scope.productsPage.totalPages;
            }

            $scope.paginationArray = $scope.generatePagesIndexes(minPageIndex, maxPageIndex);
            console.log($scope.productsPage );
            $scope.loadCart();

        });
    };


    $scope.createNewProduct = function () {
        console.log($scope.newProduct);
        $http.post(contextPath + '/api/v1/products', $scope.newProduct)
            .then(function successCallBack(response) {
                $scope.newProduct = null;
                $scope.loadPage($scope.productsPage.totalPages);
            }, function errorCallback(response) {
                console.log(response.data);
                alert("Error!!! \n" + response.data.messages);
            });
    };
    $scope.clickOnProduct = function (product) {
        console.log(product);
    }

    $scope.generatePagesIndexes = function (startPage, endPage) {
        let arr = [];
        for (let i = startPage; i < endPage + 1; i++) {
            arr.push(i);
        }
        return arr;
    }

    /*
    * Cart functions
    * */

    $scope.loadCart = function () {
        $http.get(contextPath + '/api/v1/cart')
            .then(function (response) {
                $scope.cartDto = response.data;
            });
    }

    $scope.addToCart = function (productId) {
        $http({
            url: contextPath + "/api/v1/cart/add/" + productId,
            method: "GET"
        }).then(function (response) {
            $scope.loadCart();
        })
    }

    $scope.clearCart = function () {
        $http({
            url: contextPath + "/api/v1/cart/clear",
            method: "GET"
        }).then(function (response) {
            $scope.loadCart();
        })
    }


    $scope.deleteItem = function (productTitle) {
        $http({
            url: contextPath + "/api/v1/cart/delete",
            method: "GET",
            params: {
                title: productTitle
            }
        }).then(function (response) {
            $scope.loadCart();
            console.log("OK");
        })
    }

    $scope.loadPage(1);
    $scope.loadCart();
});

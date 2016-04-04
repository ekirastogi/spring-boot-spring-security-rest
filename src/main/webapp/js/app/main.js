var app = angular.module('ekjam', ["ui.router"]);
app.config(function($stateProvider, $urlRouterProvider){
    $urlRouterProvider.otherwise("/home");

    $stateProvider
        .state('root',{
            abstract : true,
            views : {
                'navbar' : {

                },
                'content' : {}
            }
        })
        .state('home', {
            url: "/home",
            views : {
                'navbar' :{
                    templateUrl : '/templates/layout/navbar.html'
                },
                'content' : {
                    templateUrl : '/templates/layout/content.html',
                    controller: function($scope) {
                        console.log("##################");
                        $scope.data = 'Hello Angular';
                    }
                }
            }
        })
        .state('route1.list', {
            url: "/list",
            templateUrl: "route1.list.html",
            controller: function($scope){
                $scope.items = ["A", "List", "Of", "Items"];
            }
        })

        .state('route2', {
            url: "/route2",
            templateUrl: "route2.html"
        })
        .state('route2.list', {
            url: "/list",
            templateUrl: "route2.list.html",
            controller: function($scope){
                $scope.things = ["A", "Set", "Of", "Things"];
            }
        });
});

app.controller('HomeController',function($scope){
    console.log("##################");
    $scope.data = 'Hello Angular'
});

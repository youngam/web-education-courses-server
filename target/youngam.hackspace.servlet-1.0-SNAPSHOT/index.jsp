<%-- Created by IntelliJ IDEA. --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title></title>
  </head>
  <script type="text/javascript" src="js/angular.min.js"></script>

  <script>
      angular.module('myApp', [])
          .controller('MyController', MyController);

      function MyController($scope, $http) {

          $scope.getDataFromServer = function() {
              $http({
                  method : 'GET',
                  url : 'test'
              }).then(function successCallback(response) {
                  // this callback will be called asynchronously
                  // when the response is available
                  console.log(response);
                  $scope.person = response.data;
              });

          };
      };
  </script>
  <body>
  <div ng-app="myApp">
    <div ng-controller="MyController">
      <button ng-click="getDataFromServer()">Fetch data from server</button>
      <p>First Name : {{person.firstName}}</p>
      <p>Last Name : {{person.secondName}}</p>
    </div>
  </div>
  </body>
</html>
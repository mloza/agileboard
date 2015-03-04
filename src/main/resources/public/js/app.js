var app = angular.module("agileBoard", ["ngRoute", "ui.sortable"]);

app.service("stomp", ["$q", "$rootScope", function ($q, $rootScope) {
    var socket = new SockJS('/agilews');
    var client = Stomp.over(socket);

    console.log("connecting");
    client.connect({}, function () {
        var handleStories = function (data) {
            appVariables.stories = eval(data.body);

            var $taskBoard = $('.taskBoard');
            $taskBoard.css({'height': $taskBoard.height()+'px'});

            for (i in appVariables.stories) {
                var story = appVariables.stories[i];

                for (st in appVariables.states) {
                    story[appVariables.states[st]] = [];
                }

                for (j in story.tasks) {
                    var task = story.tasks[j];
                    if (!task.state) {
                        task.state = 'BACKLOG';
                    }
                    story[task.state].push(task);
                }
            }

            $rootScope.$apply();
            $taskBoard.css({'height': 'auto'});
        };
        client.subscribe("/app/stories", handleStories);
        client.subscribe("/topic/stories", handleStories);
    }, function(error) {
        alert("Error: "+error);
        document.location.href = "/";
    });

    return {
        client: client,
        saveTask: function (task, callback) {
            client.send("/app/task/update", {}, JSON.stringify(task));
            if (callback) {
                callback();
            }
        },
        saveStory: function (story, callback) {
            client.send("/app/story/update", {}, JSON.stringify(story));
            if (callback) {
                callback();
            }
        }
    }
}]);

app.config(function ($interpolateProvider, $routeProvider) {
    $routeProvider
        .when("/", {
            controller: 'mainController',
            templateUrl: 'views/stories/taskboard.html'
        }).when("/story/add", {
            controller: 'storyAddEditController',
            templateUrl: 'views/stories/add.html'
        }).when("/story/edit/:storyid", {
            controller: 'storyAddEditController',
            templateUrl: 'views/stories/add.html'
        }).when("/tasks/add/:storyid", {
            controller: 'taskAddController',
            templateUrl: 'views/tasks/add.html'
        }).when("/tasks/edit/:storyid/:taskid", {
            controller: 'taskEditController',
            templateUrl: 'views/tasks/add.html'
        });

    $interpolateProvider.startSymbol('[[');
    $interpolateProvider.endSymbol(']]');
});

var appVariables = {
    stories: {},
    states: ['BACKLOG', 'IN_DEVELOPMENT', 'SELECTED', 'BLOCKED', 'CLOSED']
};

function findStoryById(storyId) {
    for (i in appVariables.stories) {
        if (appVariables.stories[i].id == storyId) {
            return appVariables.stories[i];
        }
    }
}

function findTaskById(storyId, taskId) {
    for (i in appVariables.stories) {
        if (appVariables.stories[i].id == storyId) {
            for (j in appVariables.stories[i].tasks) {
                if (appVariables.stories[i].tasks[j].id == taskId) {
                    return appVariables.stories[i].tasks[j];
                }
            }
        }
    }
}

app.controller("mainController", ["$scope", "$location", "stomp", function ($scope, $location, stomp) {
    $scope.data = appVariables;

    $scope.receiveFn = function (event, ui) {
        var item = ui.item;
        var task = findTaskById(item.attr('data-story-id'), item.attr('data-task-id'));
        task.state = $(event.target).attr('data-state');

        stomp.saveTask(task, function () {});
    };

    $scope.closeStory = function(story) {
        story.state = "CLOSED";
        stomp.saveStory(story);
    }
}]);

app.controller("storyAddEditController", ["$scope", "$location", "$routeParams", "stomp", function ($scope, $location, $routeParams, stomp) {
    $scope.story = {
        name: ""
    };

    if($routeParams.storyid) {
        $scope.story = findStoryById($routeParams.storyid);
    }

    $scope.addStory = function () {
        stomp.saveStory($scope.story, function () {
            $location.path("/");
        });
    }
}]);

app.controller("taskAddController", ["$scope", "$location", "$routeParams", "stomp", function ($scope, $location, $routeParams, stomp) {
    $scope.task = {
        storyId: $routeParams.storyid,
        name: "",
        state: "BACKLOG"
    };

    $scope.addTask = function () {
        stomp.saveTask($scope.task, function () {
            $location.path("/");
        });
    }
}]);

app.controller("taskEditController", ["$scope", "$location", "$routeParams", "stomp", function ($scope, $location, $routeParams, stomp) {
    $scope.task = findTaskById($routeParams.storyid, $routeParams.taskid);

    $scope.addTask = function () {
        stomp.saveTask($scope.task, function () {
            $location.path("/");
        });
    }
}]);
(function ($) {
    var Router = Backbone.Model.extend({
        defaults:{
        	 interfaces: "",
        	 inventoryId: "",
        	 ports: "",
             routingTable: ""
        }
    });

    var RouterCollection = Backbone.Collection.extend({
        model:Router,
        url:'http://localhost:8080/device-manager-api/webapi/devices/routers',
        parse:function (response) {
            console.log(response);
            //response.id = response.inventoryId;
            // Parse the response and construct models
            for ( var i = 0, length = response.routers.length; i < length; i++) {

            	var currentValues = response.routers[i];
            	var devObject = {};
            	for ( var j = 0, valuesLength = currentValues.length; j < valuesLength; j++) {
            		devObject[keys[j]] = currentValues[j];
            	}

            	// push the model object
            	this.push(devObject);
            }
			console.log(this.toJSON());
			
			//return models
			return this.models;
            //return response;
        }
    });

    var RouterView = Backbone.View.extend({
        tagName:"div",
        className:"wrapper",
        template:$("#device-template").html(),

        render:function () {
            var tmpl = _.template(this.template); //tmpl is a function that takes a JSON and returns html

            this.$el.html(tmpl(this.model.toJSON())); //this.el is what we defined in tagName. use $el to get access to jQuery html() function
            return this;
        },

//        events:{
//            "click .delete":"deleteRouter"
//        },

//        deleteRouter:function () {
//            console.log('Destroying router _id: ' + this.model.get("id"));
//            //Delete model
//            this.model.destroy({
//                error:function (model, response) {
//                    console.log("Failed destroying book");
//                },
//                success:function (model, response) {
//                    console.log("Succeeded in destroying book");
//                }
//            });
//
//            //Delete view
//            this.remove();
//        }
    });

    var RouterCollectionView = Backbone.View.extend({
        el:$("#routers"),

        initialize:function () {
            //this.collection = new Library(books);
        	var self = this;
            this.collection = new RouterCollection();
            this.collection.fetch({
            	success:function(response){
            		console.log("Success fetchingg");
                    self.render();
                    //self.collection.push

            	},
                error:function () {
                    console.log(arguments);
                }
            });
            this.render();

            this.collection.on("add", this.renderRouter, this);
//            this.collection.on("remove", this.removeBook, this);
            this.collection.on("reset", this.render, this);
        },

        render:function () {
            var that = this;
    		console.log("Start rendering");

            _.each(this.collection.models, function (item) {
        		console.log("Rendering item");
                that.renderRouter(item);
            });
        },

//        events:{
//            "click #add":"addBook"
//        },
//
//        addBook:function (e) {
//            e.preventDefault();
//
//            var formData = {};
//
//            $("#addBook div").children("input").each(function (i, el) {
//                if ($(el).val() !== "") {
//                    if (el.id === 'keywords') {
//                        var keywordArray = $(el).val().split(',');
//                        var keywordObjects = [];
//                        for (var j = 0; j < keywordArray.length; j++) {
//                            keywordObjects[j] = {"keyword":keywordArray[j]};
//                        }
//                        formData[el.id] = keywordObjects;
//                    } else if (el.id === 'releaseDate'){
//                        formData[el.id] = $('#releaseDate').datepicker("getDate").getTime();
//                    } else {
//                        formData[el.id] = $(el).val();
//                    }
//                }
//            });
//
//            books.push(formData);
//
//            //this.collection.add(new Book(formData));
//            this.collection.create(formData);
//        },

//        removeBook:function (removedBook) {
//            var removedBookData = removedBook.attributes;
//
//            _.each(removedBookData, function (val, key) {
//                if (removedBookData[key] === removedBook.defaults[key]) {
//                    delete removedBookData[key];
//                }
//            });
//
//            _.each(books, function (book) {
//                if (_.isEqual(book, removedBookData)) {
//                    books.splice(_.indexOf(books, book), 1);
//                }
//            });
//        },

        renderRouter:function (item) {
            var routerView = new RouterView({
                model:item
            });
            this.$el.append(routerView.render().el);
        }
    });

    var routerCollectionView = new RouterCollectionView();
})(jQuery);
(function($){
	var Device = Backbone.Model.extend({
		defaults:{
			interfaces: "",
			inventoryId: "",
			ports: "",
			routingTable: ""
		}
	});

	var DevicesList = Backbone.Collection.extend({
		model: Device,
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
	//var numDevices = 0; TODO: control device inventoryId in order to create new devices properly...
	var devices = new DevicesList();

	var DeviceView = Backbone.View.extend({
		model: new Device(),
		tagName: 'div',
		events: {
			'click .name': 'updateName',
			'click .ip': 'updateIp',
			'click .description': 'updateDesc',
			'click .delete': 'deletee',
			'click .details': 'details',
			'blur .name': 'close',
			'blur .ip': 'close',
			'blur .description': 'close',
			'keypress .name': 'onEnterUpdate',
			'keypress .ip': 'onEnterUpdate',
			'keypress .description': 'onEnterUpdate'
		},
		initialize: function(){
			this.template = _.template($('#device-template').html()); // using
			// underscore
			// template
		},
		updateName: function(ev){
			ev.preventDefault();
			this.$('.name').attr('contenteditable',true).focus();
		},
		updateIp: function(ev){
			ev.preventDefault();
			this.$('.ip').attr('contenteditable',true).focus();
		},
		updateDesc: function(ev){
			ev.preventDefault();
			this.$('.description').attr('contenteditable',true).focus();
		},
		details: function(ev){

		},
		close: function(ev){
			var self = this;
			var name = this.$('.name').text();
			var ip = this.$('.ip').text();
			var description = this.$('.description').text();
			var id = this.$('.id').text();
			// this.model.set('name', name);
			// this.model.set('ip', ip);
			// this.model.set('description', description);
			this.model.save({ id: id ,name: name ,ip: ip, description: description }, {
				success: function(){ console.log('Successfully updated device '+ self.model.id); },
				error: function(){ console.log("Failed to update device with id = "+self.model.id); }
			});
			this.$('.name').removeAttr('contenteditable');
			this.$('.ip').removeAttr('contenteditable');
			this.$('.description').removeAttr('contenteditable');
		},
		onEnterUpdate: function(ev){
			var self = this;
			if(ev.keyCode==13){
				this.close();
				_.delay(function(){ self.$('.name').blur()},100);
				_.delay(function(){ self.$('.ip').blur()},100);
				_.delay(function(){ self.$('.description').blur()},100);
			}
		},
		deletee: function(ev){
			ev.preventDefault();
			var self = this;
			this.model.destroy({
				success: function() { devices.remove(this.model); console.log("Delete succesfully done!"); },
				error: function() { console.log("Failed to remove device with id = "+self.model.idAttribute); }
			});
		},
		render: function(){
			this.$el.html(this.template(this.model.toJSON()));
			return this;
		}
	});

	var DevicesView = Backbone.View.extend({
		model: devices,
		el: $('#devices-container'),
		initialize: function(){
			var self = this;
			this.model.on('add', this.render, this);
			this.model.on('remove', this.render,this);
			// get all devices (Backbone.sync powah!!!)
			this.model.fetch({
				success: function(response,xhr) {
					console.log("Success fetchingg");
					self.render();
				},
				error:function () {
					console.log(arguments);
				}	
			});

		},	
		render: function(){
			var self = this;
			self.$el.html('');
			_.each(this.model.toArray(),function(device,i){
				self.$el.append((new DeviceView({model: device})).render().$el);
			});
			return this;
		}

	});

	var Router = Backbone.Router.extend({
		routes: {
			'': 'index',
			'devices/:id': 'show'
		},
		index: function(){
			// enable Add Device
			console.log("INDEX");
			// disable show device
		},
		show: function(id){
			// disable add device
			console.log("SHOW");
			// enable show device
		}
	});

	$(document).ready(function(){
		$('#add-device').submit(function(ev){
			//var the_id= "RT-"+ ++numDevices;
			//var device = new Device({id:the_id,name:$('#device-name').val(),ip:$('#device-ip').val(),description:$('#device-description').val()});
			var device = new Device({inventoryId:$('#device-inventoryId').val(),ports:$('#device-ports').val(),interfaces:$('#device-interfaces').val(),routingTable:$('#device-routingTable').val()});
			devices.add(device);
			// var device2 = new Device({id:'RT-2',name:'Router
			// 2',ip:'192.168.1.12',description:'second router'});
			// var device3 = new Device({id:'RT-3',name:'Router
			// 3',ip:'192.168.1.13',description:'third router'});
			// console.log(device.get('description'));
			console.log(devices.toJSON());
//			device.save({id:device.get('id'),name:$('#device-name').val(),ip:$('#device-ip').val(),description:$('#device-description').val()},{
//			succes: function(){ consol.log("successfully saved device!");},
//			error: function(){ console.log("error saving device!");}
//			})
			return false;	
		});

		var appView = new DevicesView();
	});
	


})(jQuery);


$(function(){
    $("#loading").spin({lines: 9, // The number of lines to draw
    	  length: 24, // The length of each line
    	  width: 7, // The line thickness
    	  radius: 32, // The radius of the inner circle
    	  corners: 1, // Corner roundness (0..1)
    	  rotate: 65, // The rotation offset
    	  direction: 1, // 1: clockwise, -1: counterclockwise
    	  color: '#000', // #rgb or #rrggbb or array of colors
    	  speed: 0.9, // Rounds per second
    	  trail: 76, // Afterglow percentage
    	  shadow: true, // Whether to render a shadow
    	  hwaccel: true, // Whether to use hardware acceleration
    	  className: 'spinner', // The CSS class to assign to the spinner
    	  zIndex: 2e9, // The z-index (defaults to 2000000000)
    	  top: 'auto', // Top position relative to parent in px
    	  left: 'auto' // Left position relative to parent in px
    }).hide();
    $('#loading').ajaxStart(function(){ $(this).fadeIn(); });
    $('#loading').ajaxComplete(function(){ $(this).fadeOut(); });
});
var Messages = Backbone.Collection.extend({
    url: 'http://localhost:8080/rest/chat',
    idAttribute: 'id',
    parse: function(data) {
        App.Context.setLastIndex(data.lastIndex);
    	return data.messages;
  	}
});

var messages = new Messages();

/*var Message = Backbone.Model.extend({
    urlRoot: 'http://localhost:8080/rest/chat',
    idAttribute: 'id',
    parse: function(data) {
    	return data.messages;
  	}
});
*/
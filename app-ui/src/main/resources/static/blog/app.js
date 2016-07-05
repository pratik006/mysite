/** constant declarations and shoould be imported by all pages **/
var MONTHS = ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'];
var AM_PM = ['AM', 'PM'];
var CONTEXT_ROOT = window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));
var REST_PATH = "/rest/blog";
var ACTION_PATHS = [];
ACTION_PATHS['login'] = REST_PATH + "/login";
ACTION_PATHS['logout'] = REST_PATH + "/logout";
ACTION_PATHS['userinfo'] = "/rest/userinfo";
ACTION_PATHS['create'] = REST_PATH + "/create";
ACTION_PATHS['allBlogs'] = REST_PATH + "/blogs";
ACTION_PATHS['getBlog'] = REST_PATH + "/";
ACTION_PATHS['successfulCreateBlog'] = "index.html";

App = {Context: ''};
App.Context = {
	getValue: function(key) {
    	return this[key];
	},
	setValue: function(key, value) {
    	this[key] = value;
	}	
}

console.log(CONTEXT_ROOT);


function formatDate(date) {date = new Date(date);
	var hour = (date.getHours()%12)<10?'0'+date.getHours():date.getHours();
	var min = date.getMinutes()<10?'0'+date.getMinutes():date.getMinutes();
	return MONTHS[date.getMonth()] +' '+date.getDate()+', '+date.getFullYear()+' '+hour+':'+min+' '+AM_PM[parseInt(date.getHours()/12)];
}
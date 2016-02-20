/** constant declarations and shoould be imported by all pages **/
var MONTHS = ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'];
var AM_PM = ['AM', 'PM'];
var CONTEXT_ROOT = window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));
var REST_PATH = "/rest/blog";
var ACTION_PATHS = [];
ACTION_PATHS['login'] = REST_PATH + "/login";
ACTION_PATHS['userinfo'] = "/rest/userinfo";
ACTION_PATHS['create'] = REST_PATH + "/create";
ACTION_PATHS['allBlogs'] = REST_PATH + "/blogs";
ACTION_PATHS['getBlog'] = REST_PATH + "/";
ACTION_PATHS['successfulCreateBlog'] = "index.html";

function submitForm(form, successfn, errorfn) {
	var dataObject = new Object();
	$(form).find("input").each(function(index){
		dataObject[$(this).attr("id")] = $(this).val();
	});
	var strData = JSON.stringify(dataObject);
	console.log($(form).serialize());
	var action = $(form).attr("action");
	console.log("submit for to :"+ACTION_PATHS[action]);
	$.ajax({
   		  type: "POST",
   		  url: ACTION_PATHS[action],
   		  data: $(form).serialize(),
   		  success: successfn,
   		  error: errorfn
    	});
}

console.log(CONTEXT_ROOT);
var blogs = new Array();

function createBlogEntries(id) {
	$.ajax({url: ACTION_PATHS['allBlogs'], success: function(blogs) {
		var html = '';
		for (var i = blogs.length - 1; i >= 0; i--) {
			html += createBlogEntry(blogs[i]);
		};
		$("#"+id).html(html);
    }});
	
}

function createBlogEntry(blog) {
	var html = '<div id="blogEntry"><h2><a href="blog.html?id='+blog.id+'">'+blog.title
	+'</a></h2> <p class="lead">by <a href="../#about">'+blog.createdBy+'</a></p>'
	+'<p><span class="glyphicon glyphicon-time"></span> Posted on '
	+formatDate(blog.created)+'</p><hr>';

	if (blog.coverLink) {
		html += '<img class="img-responsive" '+'src="'+blog.coverLink+'" alt=""><hr>';
	}

	if(blog.lead) {
		html += '<p class="lead">'+blog.lead+'</p>';
	}
	if(blog.intro) {
		html += '<p>'+blog.intro+'</p>';
	}
	
	html += '<a class="btn btn-primary" href="./post/blog.html?id='+blog.id+'">Read More <span class="glyphicon glyphicon-chevron-right"></span></a><hr></div>';

	return html;
}

function formatDate(date) {date = new Date(date);
	var hour = (date.getHours()%12)<10?'0'+date.getHours():date.getHours();
	var min = date.getMinutes()<10?'0'+date.getMinutes():date.getMinutes();
	return MONTHS[date.getMonth()] +' '+date.getDate()+', '+date.getFullYear()+' '+hour+':'+min+' '+AM_PM[parseInt(date.getHours()/12)];
}

function createBlogPost(id, successfn) {
 	$.ajax({url: ACTION_PATHS['getBlog'] + id, success: successfn});
}

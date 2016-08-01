Backbone.history.start();

App = {Context: ''};
App.Context = {
  getValue: function(key) {
      return this[key];
  },
  setValue: function(key, value) {
      this[key] = value;
  } 
}

function render(tmpl_name, tmpl_data) {
    if ( !render.tmpl_cache ) { 
        render.tmpl_cache = {};
    }

    if ( ! render.tmpl_cache[tmpl_name] ) {
        var tmpl_dir = 'js/templates';
        var tmpl_url = tmpl_dir + '/' + tmpl_name + '.html';
        var tmpl_string;
        $.ajax({
            url: tmpl_url,
            method: 'GET',
            async: false,
            success: function(data) {
                tmpl_string = data;
            }
        });

        render.tmpl_cache[tmpl_name] = _.template(tmpl_string);
    }
    return render.tmpl_cache[tmpl_name](tmpl_data);
}

$.fn.serializeObject = function()
{
    var o = {};
    var a = this.serializeArray();
    $.each(a, function() {
        if (o[this.name] !== undefined) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
}

function getAttribute(blog, id) {
    if(typeof blog != 'undefined')
        return blog.get(id);
}

/*user = {};
var lastIndex = -1;
refreshTime = 10000;
$(document).ready(function() {
  $('#login').click(function() {
    var data = $('#loginForm').serialize();
    console.log(data);
    $.ajax({
      url: '/rest/blog/login',
      data: data,
      dataType: "json",
      type: "POST",
      success: function(result) {
        console.log(result);
        user = result;
        $('.viewport').load('./chat-template.html');
        $('#refresh').click();
        
      }
    });
  });
});*/
const postsDiv = document.querySelector(".posts");
const viewportDiv = document.querySelector(".viewport");
const recentPosts = document.querySelector(".recent-posts");
const context = {
  "baseUrl": "https://travelblog-618f2.firebaseio.com/",
  "posts": "https://travelblog-618f2.firebaseio.com/posts.json",
  "postDetails": "https://travelblog-618f2.firebaseio.com/post-details/",
  "cache": {}
};

window.addEventListener('load', e => {
  const curUrl = window.location.href;
  if (curUrl.split("#").length>1 && curUrl.split("#")[1].length > 0) {
    let path = curUrl.split("#")[1];
    console.log(path);
    loadBlogPost(path);
  } else {
    loadBlogs();
  }
});

window.addEventListener('hashchange', ()=> {
  const curUrl = window.location.href;
  if (curUrl.split("#").length>1 && curUrl.split("#")[1].length > 0) {
    let path = curUrl.split("#")[1];
    console.log(path);
    loadBlogPost(path);
  } else {
    console.log("loading blogpost");
    loadBlogs();
  }
});

async function loadBlogs() {
  if (!context.cache.posts) {
    const posts = await fetch(context.posts).then(resp=>resp.json());
    const sortable = new Array();
    for (let postCode in posts) {
      sortable.push(posts[postCode]);
      sortable[sortable.length-1].code = postCode;
    }
    sortable.sort((post1,post2) => { return post1.postTime - post2.postTime});
    context.cache.posts = sortable; 
  }

  viewportDiv.innerHTML = `<h1 class="my-4">My Travel Diary <small>a travel blog</small></h1><hr>`;
  context.cache.posts.map(post => viewportDiv.innerHTML += blogEntryTemplate(post));

  recentPosts.innerHTML = recentPostsTemplate(context.cache.posts);
}

async function loadBlogPost(blogCode) {
  const post = context.cache.posts.find(post => post.code == blogCode);
  fetch(context.postDetails+blogCode+".json").then(resp => resp.json()).then(postDetail => {
    post.content = postDetail.content;
    viewportDiv.innerHTML = blogPostTemplate(post);
  });
}

function blogEntryTemplate(post) {
  return `
  <div class="card mb-4">
    <img class="card-img-top" src="${post.previewUrl}" alt="Card image cap">
    <div class="card-body">
      <h2 class="card-title">${post.title}</h2>
      <p class="card-text lead">${post.intro}</p>
      <a href="#${post.code}" class="btn btn-primary">Read More &rarr;</a>
    </div>
    <div class="card-footer text-muted">
      Posted on ${post.postTime} by
      <a href="#">${post.author}</a>
    </div>
  </div>`;
}

function blogPostTemplate(post) {
  return `
    <h1 class="mt-4">${post.title}</h1>
    <!-- Author -->
    <p class="lead">
      by <a href="#">${post.author}</a>
    </p>
    <span class="small">Posted on ${post.postTime}</span>
    <hr>
    <img class="img-fluid rounded" src="${post.previewUrl}" alt="">
    <hr>
    <!-- Post Content -->
    <p>${post.content}</p>
    <!--blockquote class="blockquote">
      <p class="mb-0">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer posuere erat a ante.</p>
      <footer class="blockquote-footer">Someone famous in
        <cite title="Source Title">Source Title</cite>
      </footer>
    </blockquote-->
    <hr>
  `;
}

function recentPostsTemplate(posts) {
  return posts.reduce((resp, post) => resp.concat(`<li><a href="#${post.code}">${post.title}<a/></li>`), ``);
}


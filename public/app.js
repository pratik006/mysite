const postsDiv = document.querySelector(".posts");
const viewportDiv = document.querySelector(".viewport");
const context = {
  "baseUrl": "https://travelblog-618f2.firebaseio.com/",
  "posts": "https://travelblog-618f2.firebaseio.com/posts.json"
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
  fetch(context.posts).then(resp => resp.json()).then(posts => {
    viewportDiv.innerHTML = `<h1 class="my-4">My Travel Diary
    <small>a travel blog</small>
  </h1>
  <hr>`;
    for (let postCode in posts) {
      const post = posts[postCode];
      post.code = postCode.trim();
      viewportDiv.innerHTML += blogEntryTemplate(post);
    }
  });
}

async function loadBlogPost(blogCode) {
  fetch(context.baseUrl+"posts/"+blogCode+".json").then(resp => resp.json()).then(post => {
    viewportDiv.innerHTML = blogPostTemplate(post);
  });
}

function blogEntryTemplate(post) {
  return `
  <div class="card mb-4">
    <img class="card-img-top" src="${post.previewUrl}" alt="Card image cap">
    <div class="card-body">
      <h2 class="card-title">${post.title}</h2>
      <p class="card-text">${post.content.substring(0, 500)}</p>
      <a href="#${post.code}" class="btn btn-primary">Read More &rarr;</a>
    </div>
    <div class="card-footer text-muted">
      Posted on ${post.postDate} by
      <a href="#">${post.author}</a>
    </div>
  </div>`;
}

function blogPostTemplate(post) {
  return `
  <!-- Title -->
          <h1 class="mt-4">${post.title}</h1>
          <!-- Author -->
          <p class="lead">
            by
            <a href="#">${post.author}</a>
          </p>
          <hr>
          <!-- Date/Time -->
          <p>Posted on ${post.postTime}</p>
          <hr>
          <!-- Preview Image -->
          <img class="img-fluid rounded" src="${post.previewUrl}" alt="">
          <hr>
          <!-- Post Content -->
          <p>${post.content}</p>
          <blockquote class="blockquote">
            <p class="mb-0">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer posuere erat a ante.</p>
            <footer class="blockquote-footer">Someone famous in
              <cite title="Source Title">Source Title</cite>
            </footer>
          </blockquote>
          <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Error, nostrum, aliquid, animi, ut quas placeat totam sunt tempora commodi nihil ullam alias modi dicta saepe minima ab quo voluptatem obcaecati?</p>
          <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Harum, dolor quis. Sunt, ut, explicabo, aliquam tenetur ratione tempore quidem voluptates cupiditate voluptas illo saepe quaerat numquam recusandae? Qui, necessitatibus, est!</p>
          <hr>
  `;
}


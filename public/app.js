const postsDiv = document.querySelector(".posts");
const context = {
  "baseUrl": "https://travelblog-618f2.firebaseio.com/ved/",
  "posts": "https://travelblog-618f2.firebaseio.com/posts.json",
  "booksUrl": "https://travelblog-618f2.firebaseio.com/ved/"+"books.json"
};

window.addEventListener('load', e => {
    loadBlogs();
});

async function loadBlogs() {
  fetch(context.posts).then(resp => resp.json()).then(posts => {
    for (let postCode in posts) {
      const post = posts[postCode];
      post.code = postCode;
      postsDiv.innerHTML += blogEntryTemplate(post);
    }
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


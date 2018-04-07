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
      <p class="card-text">${post.content.substring(0, 100)}</p>
      <a href="#" class="btn btn-primary">Read More &rarr;</a>
    </div>
    <div class="card-footer text-muted">
      Posted on January 1, 2017 by
      <a href="#">Start Bootstrap</a>
    </div>
  </div>`;
}


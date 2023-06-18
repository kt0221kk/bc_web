window.onload = function() {
  // ページが読み込まれたときにrequired属性を削除
  var inputs = document.getElementsByTagName('input');
  for (var i = 0; i < inputs.length; i++) {
      if (inputs[i].hasAttribute('required')) {
          inputs[i].removeAttribute('required');
      }
  }
}

document.getElementById('getBookInfo').addEventListener('click', () => {

  event.preventDefault();
  // isbnに入力された値を取得
  var userInput = document.getElementById('isbn').value;
  var query = userInput.split(' ').join('+');
  // APIを取得
  const url = "https://api.openbd.jp/v1/get?isbn=" + query + "&pretty";

  // json
  fetch(url).then(
  response => {
      return response.json();
  }).then(data => {
      if (data[0] && data[0].summary) {  // 確認するためにこの行を追加
          for (let i = 0; i < data.length; i++) {
              // サムネイル
              const bookImg = document.getElementById('thumbnail');
              if(data[0].summary.cover){
                document.getElementById('card_book').style.display = 'block';

              }
              const bookImgSrc = data[0].summary.cover;
              console.log(bookImgSrc);
              bookImg.setAttribute('src', bookImgSrc);
              //書籍名
              document.getElementById('title').value = data[0].summary.title;
              //出版社
              document.getElementById('publisher').value = data[0].summary.publisher;
              //巻
              //作者
              document.getElementById('author').value = data[0].summary.author;
              //出版日
              document.getElementById('publication_year').value = data[0].summary.pubdate;
              //詳細
              document.getElementById('description').innerHTML = data[0].onix.CollateralDetail.TextContent[0].Text;
          }
      } else {
          console.log('No book found with the provided openbd ISBN');
          // APIを取得
          const googleurl = "https://www.googleapis.com/books/v1/volumes?q=isbn:" + query;
console.log(googleurl);
// json
fetch(googleurl).then(
response => {
    console.log(response);
    return response.json();
}).then(data => {
  // console.log(data);
  if (data.totalItems>0) {  // 確認するためにこの行を追加
        // サムネイル
        let bookImgSrc = 'https://via.placeholder.com/150';
        if (data.items[0].volumeInfo.imageLinks) {
          bookImgSrc = data.items[0].volumeInfo.imageLinks.thumbnail;
          document.getElementById('card_book').style.display = 'block';

        }
        
        const bookImg = document.getElementById('thumbnail');
        bookImg.setAttribute('src', bookImgSrc);
        //書籍名
        document.getElementById('title').value = data.items[0].volumeInfo.title;
        // //出版社
        // //作者
        if(data.items[0].volumeInfo.authors){
          document.getElementById('author').value = data.items[0].volumeInfo.authors[0];
        }
        // //出版日
        if(data.items[0].volumeInfo.publishedDate){
          document.getElementById('publication_year').value = data.items[0].volumeInfo.publishedDate;
        }
        // //詳細
        document.getElementById('description').innerHTML = data.items[0].volumeInfo.description;
  } else {
    console.log('No book found with the provided google ISBN');
  }

});

      }
      // 情報エリアの表示
      document.getElementById('inputFields').style.display = 'block';
      console.log(bookImgSrc);

      // フォームを表示した後でrequired属性を追加
      var inputs = document.getElementsByTagName('input');
      for (var i = 0; i < inputs.length; i++) {
          if (inputs[i].type != 'submit') {  // submitボタンは除く
              inputs[i].setAttribute('required', '');
          }
      }
  }).catch(err => {
      console.log('Error: ' + err)
  })
});

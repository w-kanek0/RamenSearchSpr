//ここからCSV出力＆ダウンロード
function handleDownload() {
	var bom = new Uint8Array([0xEF, 0xBB, 0xBF]);//文字コードをBOM付きUTF-8に指定
	var table = document.getElementById('ramens');//id=table1という要素を取得
	var data_csv="";//ここに文字データとして値を格納していく

	for(var i = 0;  i < table.rows.length; i++){
	  for(var j = 2; j < table.rows[i].cells.length; j++){	// 出力対象は「店舗コード」～「オープン日」
		data_csv += table.rows[i].cells[j].innerText;//HTML中の表のセル値をdata_csvに格納
		if(j == table.rows[i].cells.length-1) data_csv += "\n";//行終わりに改行コードを追加
		else data_csv += ",";//セル値の区切り文字として,を追加
	  }
	}

	var blob = new Blob([ bom, data_csv], { "type" : "text/csv" });//data_csvのデータをcsvとしてダウンロードする関数
	if (window.navigator.msSaveBlob) { //IEの場合の処理
		window.navigator.msSaveBlob(blob, "test.csv"); 
		//window.navigator.msSaveOrOpenBlob(blob, "test.csv");// msSaveOrOpenBlobの場合はファイルを保存せずに開ける
	} else {
		document.getElementById("download").href = window.URL.createObjectURL(blob);
	}

	delete data_csv;//data_csvオブジェクトはもういらないので消去してメモリを開放
}
//ここまでCSV出力＆ダウンロード

// フォームの情報を全て消去するとき
// <input type="reset">では必ずしも消去されないため
function clearFormAll() {
	for (var i=0; i<document.forms.length; ++i) {
		clearForm(document.forms[i]);
	}
}
function clearForm(form) {
	for(var i=0; i<form.elements.length; ++i) {
		clearElement(form.elements[i]);
	}
}
function clearElement(element) {
	switch(element.type) {
		case "hidden":
		case "submit":
		case "reset":
		case "button":
		case "image":
			return;
		case "file":
			return;
		case "text":
		case "password":
		case "textarea":
		case "date":
		case "time":
			element.value = "";
			return;
		case "checkbox":
		case "radio":
			element.checked = false;
			return;
		case "select-one":
		case "select-multiple":
			element.selectedIndex = 0;
			return;
		default:
	}
}

// 検索実行時
function trim_search(form) {
	// 店舗名の前後に前後に空白(半角および全角スペース)文字があった場合それを削除
	document.search.s_name.value = document.search.s_name.value.trim();
	
	// エリアは文字間に空白文字がある場合それを削除
	document.search.s_area.value = document.search.s_area.value.replace(/\s+/g, "");
	
}

// 「登録」ボタンクリック時
function register_confirm(form) {
		
		// 店舗名入力チェック
		if (document.form.name.value.trim() == ""){
			alert('店舗名を入力してください。');
			return;
		}
		// 店舗名は前後に空白(半角および全角スペース)文字があった場合それを削除
		document.form.name.value = document.form.name.value.trim();
		
		
		// エリア入力チェック
		if (document.form.area.value.trim() == ""){
			alert('エリアを入力してください。');
			return;
		}
		// エリアは文字間に空白文字がある場合それを削除
		document.form.area.value = document.form.area.value.replace(/\s+/g, "");
		
		// ジャンル入力チェック
		if (document.form.genre.value == "0"){
			alert('ジャンルを入力してください。');
			return;
		}
		
		// 開店時間入力チェック
		if (document.form.opentime.value == ""){
			alert('開店時間を入力してください。');
			return;
		}
		
		// 閉店時間入力チェック
		if (document.form.closetime.value == ""){
			alert('閉店時間を入力してください。');
			return;
		}
		
		// ジャンルを設定
		// 1:塩、2:味噌、3:醤油、4:豚骨、5:鶏白湯、6:担々麺
		var genre = "";
		switch(document.form.genre.value) {
			case "1":
				genre = "塩";
				break;
			
			case "2":
				genre = "味噌";
				break;

			case "3":
				genre = "醤油";
				break;

			case "4":
				genre = "豚骨";
				break;

			case "5":
				genre = "鶏白湯";
				break;
				
			case "6":
				genre = "担々麺";
				break;
		}

		// 評価を設定
		var valuestar = "";
		switch(document.form.value.value) {
			case "1":
				valuestar = "☆★★★★";
				break;
			case "2":
				valuestar = "☆☆★★★";
				break;
			case "3":
				valuestar = "☆☆☆★★";
				break;
			case "4":
				valuestar = "☆☆☆☆★";
				break;
			case "5":
				valuestar = "☆☆☆☆☆";
				break;
		}

		// ファイルパスが存在する場合ファイル名のみ切り取る
		var filename = "";
		if(document.form.uploadfile.value != "") {
			var pos = document.form.uploadfile.value.lastIndexOf('\\');
			filename = document.form.uploadfile.value.substring(pos + 1);
		}

		// ダイアログ表示文字列作成
		var str = "";
		if(document.form.shopid.value == "0") {

			str = "以下の内容で登録します。\n";
		} else {

			str = "店舗コード : " + document.form.shopid.value + " の情報を以下の内容で更新します。\n";
		}
		str += "実行しますか？\n\n";
		str += "店舗名：" + document.form.name.value + "\n";		// 店舗名
		str += "エリア：" + document.form.area.value + "\n";			// エリア
		str += "ジャンル：" + genre + "\n";								// ジャンル
		str += "オープン日：" + document.form.opendate.value + "\n";			// オープン日
		str += "開店時間：" + document.form.opentime.value + "\n";			// 開店時間
		str += "閉店時間：" + document.form.closetime.value + "\n";			// 閉店時間
		str += "評価(5段階)：" + valuestar + "\n";			// 5段階評価
		str += "画像ファイル名：" + filename + "\n";		// 画像ファイル名
		
		// ダイアログ表示
		let check_register = confirm(str);
		
		// 「はい」選択時
		if(check_register) { 
			form.submit();
		} else { // 「いいえ」選択時
			alert("キャンセルしました。");
			return;
		}
}

// 「レビューを見る」ボタンクリック時
function reviewlist_click() {
	checked = selectRadio();
	
	if(checked == "") {
		alert("項目が選択されていません");
	} else {
		const form = document.createElement('form');
		form.action = "DspRamenReviewSearch";
		form.modelAttribute = "ReviewlistForm";
		form.method = "post";
			
		const input = document.createElement('input');
		input.value = checked;
		input.name = "shopid";
		input.style.display = "none";
		document.body.appendChild(form);
		form.appendChild(input);

		form.submit();
	}
	
}

// 「更新」ボタンクリック時
function update_click() {
	checked = selectRadio();
	
	if(checked == "") {
		alert("項目が選択されていません");
	} else {

		const form = document.createElement('form');
		form.action = "DspRamenUpdate";
		form.modelAttribute = "dspUpdateForm";
		form.method = "post";
			
		const input = document.createElement('input');
		input.value = checked;
		input.name = "shopid";
		input.style.display = "none";

		document.body.appendChild(form);
		form.appendChild(input);
		form.submit();
	}
	
}

// 削除ボタンクリック時のアラート
function delete_click() {
	checked = selectRadio();
	
	if(checked == "") {
		alert("項目が選択されていません");
	} else {
		var table = document.getElementById('ramens');
		
		// ダイアログ表示文字列作成
		var str = "以下の情報を削除しますか？\n\n";
		
		for(var i = 1; i < table.rows.length; i++) {
			// ラジオボタンで選択された項目の場合、その項目の店舗コードと店舗名を出力
			if(table.rows[i].cells[2].innerText == checked) {
				str += "店舗コード：" + table.rows[i].cells[2].innerText + "\n";
				str += "店舗名：" + table.rows[i].cells[3].innerText + "\n";
			}
		}
		
		// ダイアログ表示
		let check_delete = confirm(str);
		
		// 「はい」選択時
		if(check_delete) {
			const form = document.createElement('form');
			form.action = "RamenDelete";
			form.modelAttribute = "DeleteForm";
			form.method = "post";
			
			const input = document.createElement('input');
			input.value = checked;
			input.name = "shopid";
			input.style.display = "none";
			document.body.appendChild(form);
			form.appendChild(input);
			form.submit();
		}
		// 「いいえ」選択時
		else {
			alert("キャンセルしました。");
		}
		
	}
}


// ラジオボタンで選択された項目を取得
function selectRadio() {
	let radio = document.getElementById("ramens_info").select;
	let checked = "";
	
	if(radio.length == undefined && radio.checked) {
		checked = radio.value;
	} else {
		for(i = 0; i < radio.length; i++) {
			if(radio[i].checked) {
				checked = radio[i].value;
			}
		}
	}
	
	return checked;
}

// 口コミ「投稿」/「更新」ボタンクリック時
function registerreview_confirm(form) {
		
	// 評価入力チェック
	if (document.form.value.value == "0"){
		alert('評価を入力してください。');
		return;
	}

	// 評価を設定
	var valuestar = "";
	switch(document.form.value.value) {
		case "1":
			valuestar = "☆★★★★";
			break;
		case "2":
			valuestar = "☆☆★★★";
			break;
		case "3":
			valuestar = "☆☆☆★★";
			break;
		case "4":
			valuestar = "☆☆☆☆★";
			break;
		case "5":
			valuestar = "☆☆☆☆☆";
			break;
	}
	
	// レビュータイトル入力チェック
	if (document.form.reviewtitle.value.trim() == ""){
		alert('タイトルを入力してください。');
		return;
	}
	// レビュータイトルは前後に空白(半角および全角スペース)文字があった場合それを削除
	document.form.reviewtitle.value = document.form.reviewtitle.value.trim();

	// レビュー本文チェック
	if (document.form.review.value.trim() == ""){
		alert('本文を入力してください。');
		return;
	}

	// ダイアログ表示文字列作成
	var str = "";
	if(document.form.reviewid.value == "0") {
		str = "口コミを登録します。\n";
	} else {
		str = "口コミを更新します。\n";
	}
	str += "実行しますか？\n\n";

	// ダイアログ表示
	let check_register = confirm(str);

	// 「はい」選択時
	if(check_register) { 
		form.submit();
	} else { // 「いいえ」選択時
		alert("キャンセルしました。");
		return;
	}
}

// レビューを削除する際の確定メッセージ
// 2023/06/06時点でidは3のみ想定
function confirm_review(id) {
	if(id == 1) {
		return confirm("この口コミを本当に投稿しますか？");
	} else if(id == 2) {
		return confirm("この口コミを本当に更新しますか？");
	} else if(id == 3) {
		// ダイアログ表示
		// return confirm("この口コミを本当に更新しますか？");
		let check_delete = confirm("この口コミを本当に削除しますか？");
		// 「はい」選択時
		if(!check_delete) { 
			alert("キャンセルしました。");
		}
		return check_delete;
	}	
}

// <input type="file" multiple>で選択したファイル名の一覧を表示
function OnFileSelect( inputElement )
{
	// ファイルリストを取得
	var fileList = inputElement.files;
 
	// ファイルの数を取得
	var fileCount = fileList.length;
 
	// HTML文字列の生成
	var fileListNames = "";
 
	// 選択されたファイルの数だけ処理する
	for ( var i = 0; i < fileCount; i++ ) {
		// ファイルを取得
		var file = fileList[i];
 
		// ファイルの情報を文字列に格納
		// fileListNames += "<img src=\"" + file.name + "\"  width=\"100\" height=\"80\"><br/>";
		 fileListNames += file.name + "<br/>";
	}
	fileListNames += "<br/>";
 
	// 結果のHTMLを流し込む
	document.getElementById( "ID001" ).innerHTML = fileListNames;
}

// ログイン時
function login_confirm(form) {
	// ユーザーIDが6文字未満の場合
	if(document.form.userid.value.trim() == "") {
		alert("ユーザーIDが入力されていません。");
		return;
	}
	
	// ユーザーIDが6文字未満の場合
	if(document.form.password.value.trim() == "") {
		alert("パスワードが入力されていません。");
		return;
	}
	
	// フォーム送信
	form.submit();
}


// ユーザー登録時
function user_register_confirm(form) {

	// ユーザーIDが6文字未満の場合
	if(document.form.userid.value.trim().length < 6) {
		alert("ユーザーIDは6～20時の半角英数字で入力してください。");
		return;
	}

	// パスワードが8文字未満の場合
	if(document.form.password.value.length < 8) {
		alert("パスワードは8～20字の半角英数字で入力してください。");
		return;
	}

	// メールアドレスが空の場合
	if(document.form.email.value.trim() == "") {
		alert("メールアドレスが入力されていません。");
		return;
	}

	// メールアドレスに"@"が含まれていない場合
	if(document.form.email.value.trim().indexOf('@') == -1) {
		alert("メールアドレスの形式ではありません。");
		return;
	}
	
	var str = "以下ユーザーを登録します。\n実行しますか？\n\n"
		str += "ユーザーID：" + document.form.userid.value + "\n";
		str += "ユーザー名：" + document.form.username.value + "\n";
		str += "メールアドレス：" + document.form.email.value + "\n";

	let register_check = confirm(str);

	if(register_check == true) {
		// フォーム送信
		form.submit();
	}
	// いいえ選択時
	else{
		alert("ユーザー登録をキャンセルしました");
	}
}
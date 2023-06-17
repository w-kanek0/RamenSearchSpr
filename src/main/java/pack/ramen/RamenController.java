package pack.ramen;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import cnt.Constant;
import dto.LoginDto;
import dto.RamenDto;

/**
 * 
 * コントローラークラス（初期起動・共通）
 *
 */
@Controller
@SessionAttributes(value = "loginName")
public class RamenController {
	
	@Autowired
	private RamenService ramenService;
	
	@Autowired
	HttpSession session; 

	/**
	 * ラーメン屋情報テーブルのデータを全件取得する
	 * @param model : モデル
	 * @return 遷移先画面
	 */
	@RequestMapping(value = "/DspRamenSearch")
	public String ramenlistall(Model model) {
				
		// 情報取得
		List<RamenDto> list;
		try {
			list = ramenService.getAllRamenList();
			if(!(list == null || list.size() == 0)) {
				
				model.addAttribute("ramenList", list);
			
			} else {
				model.addAttribute("ramenList", null);
			}
			
			//検索フォームの値の設定
			model.addAttribute("search", null);
		
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		return "jsp/Ramenlist";
	}
	
	/**
	 * ラーメン屋情報テーブルのデータで検索条件に一致するデータを取得する
	 * @param model : モデル
	 * @return 遷移先画面
	 */
	@RequestMapping(value = "/RamenSearch", method = RequestMethod.POST)
	public String searchramenlist(Model model, @ModelAttribute("searchForm")RamenForm ramenForm) {
		
		RamenDto dto = new RamenDto();		// 検索条件を格納
		
		dto.setShopName(ramenForm.getS_name());
		dto.setArea(ramenForm.getS_area());
		dto.setGenreid(Integer.parseInt(ramenForm.getS_genre()));
		dto.setValue(Integer.parseInt(ramenForm.getS_value()));
		dto.setEquality(ramenForm.getEquality());
		dto.setInputOpentime(ramenForm.getS_opentime());
		dto.setInputOpendayStart(ramenForm.getS_opendatestart());
		dto.setInputOpendayEnd(ramenForm.getS_opendateend());
		
//		model.addAttribute("", );
		
		try {
			// 情報取得
			List<RamenDto> list = ramenService.getRamenListForSearch(dto);
			if(!(list == null || list.size() == 0)) {
				
				model.addAttribute("ramenList", list);
			
			} else {
				model.addAttribute("ramenList", null);
			}
			
			//検索フォームの値の設定
			model.addAttribute("search", dto);
		
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		return "jsp/Ramenlist";
	}
	
	/**
	 * ラーメン屋口コミ情報を全件取得する
	 * @param model : モデル
	 * @return 遷移先画面
	 */
	@RequestMapping(value = "/DspRamenReviewSearch", method = RequestMethod.POST)
	public String ramenreviewlist(Model model, @ModelAttribute("ReviewlistForm")RamenForm ramenForm) {
		
		RamenDto dto = new RamenDto();		// 検索条件を格納

		// ModelAttributeから店舗コードを取得
		int shopid = Integer.parseInt(ramenForm.getShopid());

		try {
			dto = ramenService.getRamenInfoForUpdate(shopid);	// 指定した店舗コードに一致する店舗情報を取得
																// ただし使用するのは店舗名のみ

			// ログイン中のユーザーIDをセッションから取得し設定
			LoginDto user = (LoginDto)session.getAttribute("user");
			dto.setUserid(user.getUserid());

			// 該当の店舗及びユーザーで口コミが投稿済みかを確認する
			int count = ramenService.getReviewCount(dto);
			dto.setReviewCount(count);
/*			System.out.println("ShopName(RamenDto) : " + dto.getShopName());
			System.out.println("Userid(RamenDto) : " + dto.getUserid());
*/			
			model.addAttribute("ramen", dto);
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		try {
			// 口コミ情報一覧取得
			List<RamenDto> list = ramenService.getRamenReviewList(shopid);
			if(!(list == null || list.size() == 0)) {
				// 設定した店舗コードに該当する全口コミ情報に格納されている画像情報を取得
				for(int i = 0; i < list.size(); i ++) {
					RamenDto dto2 = list.get(i);
					List<String> filenames = ramenService.getImageList(dto2.getReviewid());
					if(filenames != null) {
						dto2.setFilenames(filenames);
						list.set(i, dto2);
					}
				}
				
				model.addAttribute("reviewList", list);
			
			} else {
				model.addAttribute("reviewList", null);
			}
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		return "jsp/RamenReviewlist";
	}
	
	/**
	 * ラーメン屋情報テーブルのデータを新規登録画面を出力
	 * @param model : モデル
	 * @return 遷移先画面
	 */
	@RequestMapping(value = "/DspRamenInsert", method = RequestMethod.POST)
	public String dspregister(Model model, @ModelAttribute("dspRegisterForm")RamenForm ramenForm) {
		RamenDto dto = new RamenDto();
		dto.setShopid(Integer.parseInt(ramenForm.getShopid()));

		model.addAttribute("ramen", dto);
		
		return "jsp/Ramenregister";
	}
	
	/**
	 * ラーメン屋情報の新規登録画面から受け取った店舗情報をt_shopに登録
	 * @param model : モデル
	 * @return 遷移先画面
	 */
	@RequestMapping(value = "/RamenInsert", method = RequestMethod.POST)
	public String register(Model model, @ModelAttribute("RegisterForm")RamenForm ramenForm) {

		RamenDto dto = new RamenDto();		// 登録情報を格納
		
		dto.setShopName(ramenForm.getName());		// 店舗名
		dto.setArea(ramenForm.getArea());			// エリア
		dto.setGenreid(Integer.parseInt(ramenForm.getGenre()));	// ジャンルID
		dto.setValue(Integer.parseInt(ramenForm.getValue()));		// 評価ID

		if(ramenForm.getOpendate() != null && !ramenForm.getOpendate().isEmpty() ) {
			dto.setOpenday(Date.valueOf(ramenForm.getOpendate()));				// オープン日
		}
		dto.setOpentime(Time.valueOf(ramenForm.getOpentime() + ":00"));				// 開店時刻
		dto.setClosetime(Time.valueOf(ramenForm.getClosetime() + ":00"));			// 閉店時刻

		
		// ファイルのアップロード処理
		// ファイルが選択されている場合はそのファイル名を入力
		MultipartFile uploadfile = ramenForm.getUploadfile();
		
		if (StringUtils.hasLength(uploadfile.getOriginalFilename())) {
			String path = Constant.UPLOAD_THUMBNAIL_PATH;
			File file = new File(path + uploadfile.getOriginalFilename());
			try {
			      // アップロードされたファイルストリームを取得する。
			      InputStream fileStream = uploadfile.getInputStream();

			      // ストリームからファイルを作成する。				
			      FileUtils.copyInputStreamToFile(fileStream, file);
			} catch (IOException e) {
				FileUtils.deleteQuietly(file);
			    e.printStackTrace();
			}
			dto.setFilename(uploadfile.getOriginalFilename());
		}
		
		try {
			ramenService.insertRamenInfo(dto);
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		ramenForm.setShopid("0");
		return dspregister(model, ramenForm);
	}
	
	/**
	 * ラーメン屋情報テーブルのデータ更新画面を出力
	 * 該当の店舗コードに紐づいた店舗情報のみ取得して出力
	 * @param model : モデル
	 * @return 遷移先画面
	 */
	@RequestMapping(value = "/DspRamenUpdate", method = RequestMethod.POST)
	public String dspupdate(Model model, @ModelAttribute("dspUpdateForm")RamenForm ramenForm) {
		
		int shopid = Integer.parseInt(ramenForm.getShopid());
		try {
			RamenDto dto = ramenService.getRamenInfoForUpdate(shopid);
			model.addAttribute("ramen", dto);
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		return "jsp/Ramenregister";
	}
	
	/**
	 * ラーメン屋情報のデータ更新画面から受け取った店舗情報を基に_shopの情報を変更
	 * @param model : モデル
	 * @return 遷移先画面
	 */
	@RequestMapping(value = "/RamenUpdate", method = RequestMethod.POST)
	public String update(Model model, @ModelAttribute("UpdateForm")RamenForm ramenForm) {


		RamenDto dto = new RamenDto();		// 登録情報を格納
		
		dto.setShopName(ramenForm.getName());		// 店舗名
		dto.setArea(ramenForm.getArea());			// エリア
		dto.setGenreid(Integer.parseInt(ramenForm.getGenre()));	// ジャンルID
		dto.setValue(Integer.parseInt(ramenForm.getValue()));		// 評価ID

		if(ramenForm.getOpendate() != null && !ramenForm.getOpendate().isEmpty() ) {
			dto.setOpenday(Date.valueOf(ramenForm.getOpendate()));				// オープン日
		}
		dto.setOpentime(Time.valueOf(ramenForm.getOpentime() + ":00"));				// 開店時刻
		dto.setClosetime(Time.valueOf(ramenForm.getClosetime() + ":00"));			// 閉店時刻
		dto.setShopid(Integer.parseInt(ramenForm.getShopid())); 	// 店舗コード

		// ファイルのアップロード処理
		// ファイルが選択されている場合はそのファイル名を入力
		MultipartFile uploadfile = ramenForm.getUploadfile();
		
		if (StringUtils.hasLength(uploadfile.getOriginalFilename())) {
			String path = Constant.UPLOAD_THUMBNAIL_PATH;
			File file = new File(path + uploadfile.getOriginalFilename());
			try {
			      // アップロードされたファイルストリームを取得する。
			      InputStream fileStream = uploadfile.getInputStream();

			      // ストリームからファイルを作成する。				
			      FileUtils.copyInputStreamToFile(fileStream, file);
			} catch (IOException e) {
				FileUtils.deleteQuietly(file);
			    e.printStackTrace();
			}
			dto.setFilename(uploadfile.getOriginalFilename());
		}
		
		try {
			ramenService.updateRamenInfo(dto);
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		// ramenForm.setShopid("0");
		return dspupdate(model, ramenForm);
		// return "jsp/Ramenregister";
	}
	
	@RequestMapping(value = "/RamenDelete", method = RequestMethod.POST)
	public String delete(Model model, @ModelAttribute("DeleteForm")RamenForm ramenForm) {
		
		int shopid = Integer.parseInt(ramenForm.getShopid());
		
		try {
			
			// 削除対象の店舗情報の口コミに投稿された写真ファイル名一覧を取得
			List<String> deleteFilenames = ramenService.getImageListFromShopid(shopid);
			// System.out.println("DeleteFileNames : " + deleteFilenames);
			// 取得したファイルをアップロードフォルダから削除
			if(deleteFilenames != null && deleteFilenames.size() > 0) {
				for(String filename: deleteFilenames) {
					// System.out.println("DeleteFileName : " + filename);
					String path = Constant.UPLOAD_REVIEW_PATH;
					File file = new File(path + filename);
					file.delete();
				}
			}
						
			// 該当の店舗に紐づいた口コミ情報にアップロード済みの画像情報を削除
			ramenService.deleteImageByShopid(shopid);
						
			// 該当の店舗に紐づいた口コミ情報を削除
			ramenService.deleteReviewByShopid(shopid);
			
			// t_shopから該当店舗コードのラーメン屋情報を削除
			ramenService.deleteRamenInfo(shopid);
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		return ramenlistall(model);
	}
	
	/**
	 * ラーメン屋口コミ情報テーブルのデータを新規登録画面を出力
	 * @param model : モデル
	 * @return 遷移先画面
	 */
	@RequestMapping(value = "/DspRamenReviewInsert", method = RequestMethod.POST)
	public String dspreviewregister(Model model, @ModelAttribute("dspReviewRegisterForm")RamenForm ramenForm) {
		RamenDto dto = new RamenDto();
		dto.setShopid(Integer.parseInt(ramenForm.getShopid()));
		dto.setShopName(ramenForm.getName());
		dto.setReviewid(0);	// 口コミIDは0に設定する。
		
		model.addAttribute("review", dto);
		
		return "jsp/RamenReviewregister";
	}
	
	/**
	 * ラーメン屋口コミ情報をt_reviewに新規登録
	 * @param model : モデル
	 * @return 遷移先画面
	 */
	@RequestMapping(value = "/RamenReviewInsert", method = RequestMethod.POST)
	public String reviewregister(Model model, @ModelAttribute("reviewRegisterForm")RamenForm ramenForm) {

		RamenDto dto = new RamenDto();		// 登録情報を格納
		
		dto.setShopid(Integer.parseInt(ramenForm.getShopid()));		// 店舗コード
		dto.setShopName(ramenForm.getName());		// 店舗名(画面の表示で使用)
		
		// ログイン中のユーザーIDをセッションから取得し設定
		LoginDto user = (LoginDto)session.getAttribute("user");
		dto.setUserid(user.getUserid());
		
		dto.setValue(Integer.parseInt(ramenForm.getValue()));	// 評価
		dto.setReviewTitle(ramenForm.getReviewtitle());	// 口コミタイトル
		dto.setReview(ramenForm.getReview());		// 口コミ本文
		dto.setVisitday(Date.valueOf(ramenForm.getVisitday()));	// 来訪日
		
		
		// ファイルのアップロード処理
		// ファイルが選択されている場合はそのファイル名を入力
		List<MultipartFile> uploadfiles = ramenForm.getUploadfiles();
		// ファイルアップロード処理を行う
		List<String> filenames = new ArrayList<String>();
		
		// ファイルがアップロードされた場合
		if(uploadfiles != null && !uploadfiles.isEmpty() 
				&& !uploadfiles.get(0).getOriginalFilename().isEmpty()) {
			for(MultipartFile uploadfile: uploadfiles) {
				String path = Constant.UPLOAD_REVIEW_PATH;
				File file = new File(path + uploadfile.getOriginalFilename());
				try {
					// アップロードされたファイルストリームを取得する。
					InputStream fileStream = uploadfile.getInputStream();

					// ストリームからファイルを作成する。				
					FileUtils.copyInputStreamToFile(fileStream, file);
				} catch (IOException e) {
					FileUtils.deleteQuietly(file);
					e.printStackTrace();
				}
				filenames.add(uploadfile.getOriginalFilename());
			}
		}
		dto.setFilenames(filenames);
		
		try {
			
			// 口コミ情報の2重登録防止のため。
			// 口コミ情報を登録した後一覧に遷移するがそこでF5やブラウザの更新ボタンを押したときなど
			if(ramenService.getReviewCount(dto) == 0) {
				ramenService.insertRamenReviewInfo(dto);	// t_reviewテーブルに口コミ情報を挿入
				
				// 挿入した口コミ情報の口コミIDを取得。
				ramenService.getReviewid(dto);
				
				// 画像情報テーブルにアップロードした画像ファイル分を挿入
				for(String filename: dto.getFilenames()) {
					dto.setFilename(filename);
					ramenService.insertImage(dto);
				}
			}
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		// 該当店舗の口コミ情報一覧を取得して表示
		return ramenreviewlist(model, ramenForm);
	}
	
	/**
	 * ラーメン屋口コミ情報テーブルのデータ更新画面を出力
	 * 該当の店舗コードに紐づいた店舗情報のみ取得して出力
	 * @param model : モデル
	 * @return 遷移先画面
	 */
	@RequestMapping(value = "/DspRamenReviewUpdate", method = RequestMethod.POST)
	public String dspreviewupdate(Model model, @ModelAttribute("dspReviewUpdateForm")RamenForm ramenForm) {
		
		int reviewid = Integer.parseInt(ramenForm.getReviewid());
		try {
			// フォームから取得した口コミIDに該当する口コミ情報を取得
			RamenDto dto = ramenService.getRamenReviewInfoForUpdate(reviewid);
			
			// フォームから取得した口コミIDに該当する画像ファイル名を一覧取得
			List<String> filenames = ramenService.getImageList(reviewid);
			dto.setFilenames(filenames);
			
			// リクエストパラメータから店舗コード、店舗名を取得
			dto.setShopid(Integer.parseInt(ramenForm.getShopid()));	// 店舗コード
			dto.setShopName(ramenForm.getName());		// 店舗名
			dto.setReviewid(reviewid);	// 口コミID
		
			model.addAttribute("review", dto);

		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		return "jsp/RamenReviewregister";
	}
	
	/**
	 * ラーメン屋口コミ情報を変更
	 * @param model : モデル
	 * @return 遷移先画面
	 */
	@RequestMapping(value = "/RamenReviewUpdate", method = RequestMethod.POST)
	public String reviewupdate(Model model, @ModelAttribute("reviewUpdateForm")RamenForm ramenForm) {

		RamenDto dto = new RamenDto();		// 登録情報を格納
		
		dto.setShopid(Integer.parseInt(ramenForm.getShopid()));		// 店舗コード
		dto.setShopName(ramenForm.getName());		// 店舗名(画面の表示で使用)
		
		// ログイン中のユーザーIDをセッションから取得し設定
		LoginDto user = (LoginDto)session.getAttribute("user");
		dto.setUserid(user.getUserid());

		dto.setValue(Integer.parseInt(ramenForm.getValue()));	// 評価
		dto.setReviewTitle(ramenForm.getReviewtitle());	// 口コミタイトル
		dto.setReview(ramenForm.getReview());		// 口コミ本文
		dto.setVisitday(Date.valueOf(ramenForm.getVisitday()));	// 来訪日
		
		dto.setReviewid(Integer.parseInt(ramenForm.getReviewid()));		// 口コミコード
		
		// ファイルのアップロード処理
		// ファイルが選択されている場合はそのファイル名を入力
		List<MultipartFile> uploadfiles = ramenForm.getUploadfiles();
		// ファイルアップロード処理を行う
		List<String> filenames = new ArrayList<String>();
		
		if(uploadfiles != null && !uploadfiles.isEmpty()
				&& !uploadfiles.get(0).getOriginalFilename().isEmpty()) {
			for(MultipartFile uploadfile: uploadfiles) {
				String path = Constant.UPLOAD_REVIEW_PATH;
				File file = new File(path + uploadfile.getOriginalFilename());
				try {
					// アップロードされたファイルストリームを取得する。
					InputStream fileStream = uploadfile.getInputStream();

					// ストリームからファイルを作成する。				
					FileUtils.copyInputStreamToFile(fileStream, file);
				} catch (IOException e) {
					FileUtils.deleteQuietly(file);
					e.printStackTrace();
				}
				filenames.add(uploadfile.getOriginalFilename());
			}
		}
		dto.setFilenames(filenames);	

		try {
			// 画像情報テーブルにアップロードした画像ファイル分を挿入
			for(String filename: dto.getFilenames()) {
				dto.setFilename(filename);
				ramenService.insertImage(dto);
			}

			ramenService.updateRamenReviewInfo(dto);	// t_reviewテーブルの口コミ情報を更新
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		// 該当店舗の口コミ情報一覧を取得して表示
		return ramenreviewlist(model, ramenForm);
	}
	
	/**
	 * ラーメン屋口コミ情報をt_reviewから削除
	 * @param model : モデル
	 * @return 遷移先画面
	 */
	@RequestMapping(value = "/RamenReviewDelete", method = RequestMethod.POST)
	public String reviewdelete(Model model, @ModelAttribute("RamenReviewDelete")RamenForm ramenForm) {
		
		int reviewid = Integer.parseInt(ramenForm.getReviewid());
	
		try {
			// 該当口コミIDの画像ファイル情報一覧をを画像テーブルから取得。
			List<String> filenames = ramenService.getImageList(reviewid);
			
			if(filenames != null && filenames.size() > 0) {
				
				for(String filename: filenames) {
					String path = Constant.UPLOAD_REVIEW_PATH;
					File file = new File(path + filename);
					
					// 取得したファイル名をアップロードフォルダから削除
					file.delete();
				}
			}
			
			ramenService.deleteImage(reviewid);		// 該当口コミIDの画像ファイル情報を画像テーブルから削除
			ramenService.deleteRamenReviewInfo(reviewid);	// 該当口コミIDの口コミ情報をテーブルから削除
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		// 該当店舗の口コミ情報一覧を取得して表示
		return ramenreviewlist(model, ramenForm);
	}
}


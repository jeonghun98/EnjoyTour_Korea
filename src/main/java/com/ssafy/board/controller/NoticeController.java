//package com.ssafy.board.controller;
//
//import java.io.IOException;
//import java.net.URLEncoder;
//import java.util.Collection;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletConfig;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import com.ssafy.board.model.BoardDto;
//import com.ssafy.board.model.service.BoardService;
//import com.ssafy.board.model.service.NoticeServiceImpl;
//import com.ssafy.member.model.MemberDto;
//import com.ssafy.util.PageNavigation;
//import com.ssafy.util.ParameterCheck;
//
//@WebServlet("/noticeArticle")
//public class NoticeController extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//
//	private int pgno;
//	private String key;
//	private String word;
//	private String queryStrig;
//	
//	private BoardService boardService;
//
//	@Override
//	public void init(ServletConfig config) throws ServletException {
//		super.init(config);
//		boardService = NoticeServiceImpl.getBoardService();
//	}
//
//	protected void doGet(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		String action = request.getParameter("action");
//		
//		pgno = ParameterCheck.notNumberToOne(request.getParameter("pgno"));
//		key = ParameterCheck.nullToBlank(request.getParameter("key"));
//		word = ParameterCheck.nullToBlank(request.getParameter("word"));
//		queryStrig = "&pgno=" + pgno + "&key=" + key + "&word=" + URLEncoder.encode(word, "utf-8");
//
//		String path = "";
//		if ("list".equals(action)) {
//			path = list(request, response);
//			forward(request, response, path);
//		} else if ("view".equals(action)) {
//			path = view(request, response);
//			forward(request, response, path);
//		} else if ("mvwrite".equals(action)) {
//			path = "/notice/write.jsp";
//			redirect(request, response, path);
//		} else if ("write".equals(action)) {
//			path = write(request, response);
//			redirect(request, response, path);
//		} else if ("mvmodify".equals(action)) {
//			path = mvModify(request, response);
//			forward(request, response, path);
//		} else if ("modify".equals(action)) {
//			path = modify(request, response);
//			forward(request, response, path);
//		} else if ("delete".equals(action)) {
//			path = delete(request, response);
//			redirect(request, response, path);
//		} else {
//			redirect(request, response, path);
//		}
//	}
//
//	protected void doPost(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		request.setCharacterEncoding("utf-8");
//		doGet(request, response);
//	}
//
//	private void forward(HttpServletRequest request, HttpServletResponse response, String path)
//			throws ServletException, IOException {
//		RequestDispatcher dispatcher = request.getRequestDispatcher(path);
//		dispatcher.forward(request, response);
//	}
//
//	private void redirect(HttpServletRequest request, HttpServletResponse response, String path) throws IOException {
//		response.sendRedirect(request.getContextPath() + path);
//	}
//
//	private String list(HttpServletRequest request, HttpServletResponse response) {
//		HttpSession session = request.getSession();
//		try {
//			Map<String, String> map = new HashMap<String, String>();
//			map.put("pgno", pgno + "");
//			map.put("key", key);
//			map.put("word", word);
//			
//			String sortvalue = request.getParameter("sortvalue");
//			if(sortvalue == null) sortvalue = "article_no";
//			List<BoardDto> list = boardService.listArticle(map, sortvalue); // 글목록
////			list -> sort(TimSort)
////			BoardDto Arr[] = list.toArray(new BoardDto[list.size()]);
//			request.setAttribute("articles", list);
//			
//			PageNavigation pageNavigation = boardService.makePageNavigation(map);
//			request.setAttribute("navigation", pageNavigation);
//			request.setAttribute("sortvalue", sortvalue);
//
//			return "/notice/list.jsp";
//		} catch (Exception e) {
//			e.printStackTrace();
//			request.setAttribute("msg", "글목록 출력 중 문제 발생!!!");
//			return "/error/error.jsp";
//		}
//	}
//	
//	private String view(HttpServletRequest request, HttpServletResponse response) {
//		HttpSession session = request.getSession();
//		int articleNo = Integer.parseInt(request.getParameter("articleno"));
//		try {
//			BoardDto boardDto = boardService.getArticle(articleNo);
//			boardService.updateHit(articleNo);
//			request.setAttribute("article", boardDto);
//
//			return "/notice/view.jsp";
//		} catch (Exception e) {
//			e.printStackTrace();
//			request.setAttribute("msg", "글내용 출력 중 문제 발생!!!");
//			return "/error/error.jsp";
//		}
//	}
//
//	private String write(HttpServletRequest request, HttpServletResponse response) {
//		HttpSession session = request.getSession();
//		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
//		if (memberDto != null) {
//			BoardDto boardDto = new BoardDto();
//			boardDto.setUserId(memberDto.getUserId());
//			boardDto.setSubject(request.getParameter("subject"));
//			boardDto.setContent(request.getParameter("content"));
//			try {
//				boardService.writeArticle(boardDto);
//				return "/notice/register.jsp";
//			} catch (Exception e) {
//				e.printStackTrace();
//				request.setAttribute("msg", "글작성 중 문제 발생!!!");
//				return "/error/error.jsp";
//			}
//		} else
//			return "/index.jsp";
//	}
//
//	private String mvModify(HttpServletRequest request, HttpServletResponse response) {
//		HttpSession session = request.getSession();
//		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
//		if (memberDto != null) {
//		int articleNo = Integer.parseInt(request.getParameter("articleno"));
//		try {
//			BoardDto boardDto = boardService.getArticle(articleNo);
//			request.setAttribute("article", boardDto);
//			return "/notice/modify.jsp";
//		} catch (Exception e) {
//			e.printStackTrace();
//			return "/error/error.jsp";
//		}
//	} else
//		return "/index.jsp";
//		
//	}
//
//	private String modify(HttpServletRequest request, HttpServletResponse response) {
//		HttpSession session = request.getSession();
//		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
//		if (memberDto != null) {
//		int articleNo = Integer.parseInt(request.getParameter("articleno"));
//		BoardDto boardDto = new BoardDto();
//		boardDto.setSubject(request.getParameter("subject"));
//		boardDto.setContent(request.getParameter("content"));
//		boardDto.setArticleNo(articleNo);
//		try {
//			boardService.modifyArticle(boardDto);
//			boardDto = boardService.getArticle(articleNo);
//			request.setAttribute("article", boardDto);
//			return "/notice/view.jsp";
//		} catch (Exception e) {
//			e.printStackTrace();
//			return "/error/error.jsp";
//		}
//	} else
//		return "/index.jsp";
//	}
//
//	private String delete(HttpServletRequest request, HttpServletResponse response) {
//		HttpSession session = request.getSession();
//		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
//		if (memberDto != null) {
//			int articleNo = Integer.parseInt(request.getParameter("articleno"));
//			try {
//				boardService.deleteArticle(articleNo);
//				return "/noticeArticle?action=list" + queryStrig;
//			} catch (Exception e) {
//				e.printStackTrace();
//				request.setAttribute("msg", "글삭제 중 문제 발생!");
//				return "/error/error.jsp";
//			}
//		} else
//			return "/index.jsp";
//	
//	}
//	
//	public final class TimSort {
//		static final int RUN = 32;
//
//		public void insertionSort(BoardDto[] arr, int left, int right) {
//			for (int i = left + 1; i <= right; i++) {
//				int temp = arr[i].getHit();
//				int j = i - 1;
//				while (j >= 0 && arr[j].getHit() > temp && j >= left) {
//					arr[j + 1] = arr[j];
//					j--;
//				}
//				arr[j + 1].setHit(temp);
//			}
//		}
//		public void merge(BoardDto[] arr, int left, int mid, int right) {
//
//			int leftArryLen = mid - left + 1, rightArrLen = right - mid;
//			BoardDto[] leftArr = new BoardDto[leftArryLen];
//			BoardDto[] rightArr = new BoardDto[rightArrLen];
//
//			for (int x = 0; x < leftArryLen; x++) {
//				leftArr[x] = arr[left + x];
//			}
//
//			for (int x = 0; x < rightArrLen; x++) {
//				rightArr[x] = arr[mid + 1 + x];
//			}
//
//			int i = 0;
//			int j = 0;
//			int k = left;
//
//			while (i < leftArryLen && j < rightArrLen) {
//				if (leftArr[i].getHit() <= rightArr[j].getHit()) {
//					arr[k] = leftArr[i];
//					i++;
//				} else {
//					arr[k] = rightArr[j];
//					j++;
//				}
//				k++;
//			}
//			while (i < leftArryLen) {
//				arr[k] = leftArr[i];
//				k++;
//				i++;
//			}
//			while (j < rightArrLen) {
//				arr[k] = rightArr[j];
//				k++;
//				j++;
//			}
//		}
//
//		public void timSort(BoardDto[] arr, String get) {
//			int length = arr.length;
//
//			for (int i = 0; i < length; i += RUN) {
//				insertionSort(arr, i, Math.min((i + 31), (length - 1)));
//			}
//
//			for (int size = RUN; size < length; size = 2 * size) {
//				for (int left = 0; left < length; left += 2 * size) {
//					int mid = left + size - 1;
//					int right = Math.min((left + 2 * size - 1), (length - 1));
//					merge(arr, left, mid, right);
//				}
//			}
//		}
//	}
//}

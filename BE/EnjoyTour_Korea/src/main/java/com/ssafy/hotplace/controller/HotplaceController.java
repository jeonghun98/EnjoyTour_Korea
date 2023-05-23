package com.ssafy.hotplace.controller;

import java.io.File;
import java.nio.file.Path;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.hotplace.model.FileInfoDto;
import com.ssafy.hotplace.model.HotplaceDto;
import com.ssafy.hotplace.model.service.HotplaceService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@RequestMapping("/hotplace")
@Api(tags= {"핫플레이스 관리"})
public class HotplaceController {
	
	private static final Logger logger = LoggerFactory.getLogger(HotplaceController.class);
	
	@Value("${file.path}")
	private String upload_path;
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	private HotplaceService hotplaceService;

	@Autowired
	public HotplaceController(HotplaceService hotplaceService) {
		super();
		this.hotplaceService = hotplaceService;
	}

	@ApiOperation(value = "", notes = "핫플레이스를 <b>등록</b>합니다")
	@ApiResponses({@ApiResponse(code = 200, message ="핫플 등록 OK"), @ApiResponse(code = 500, message ="서버 에러")})
	@PostMapping()
	public ResponseEntity<?> hotplaceWrite(HotplaceDto hotplaceDto,  @RequestPart("thumbNail") List<MultipartFile> files){
		logger.debug("hotplaceWrite hotplaceDto: "+hotplaceDto.toString());
		try {
//			FileUpload 관련 설정.
			System.out.println(hotplaceDto);
			System.out.println(files.get(0).getOriginalFilename());
			logger.debug("MultipartFile.isEmpty : {}", files.get(0).isEmpty());
			if (!files.get(0).isEmpty()) {
//				String realPath = servletContext.getRealPath(upload_path);
//				String realPath = servletContext.getRealPath("/resources/img");
				String today = new SimpleDateFormat("yyMMdd").format(new Date());
				String saveFolder = upload_path + File.separator + today;
				logger.debug("저장 폴더 : {}", saveFolder);
				File folder = new File(saveFolder);
				if (!folder.exists())
					folder.mkdirs();
				List<FileInfoDto> fileInfos = new ArrayList<FileInfoDto>();
				for (MultipartFile mfile : files) {
					FileInfoDto fileInfoDto = new FileInfoDto();
					String originalFileName = mfile.getOriginalFilename();
					if (!originalFileName.isEmpty()) {
						String saveFileName = UUID.randomUUID().toString()
								+ originalFileName.substring(originalFileName.lastIndexOf('.'));
						fileInfoDto.setSaveFolder(today);
						fileInfoDto.setOriginalFile(originalFileName);
						fileInfoDto.setSaveFile(saveFileName);
						logger.debug("원본 파일 이름 : {}, 실제 저장 파일 이름 : {}", mfile.getOriginalFilename(), saveFileName);
						mfile.transferTo(new File(folder, saveFileName));
					}
					fileInfos.add(fileInfoDto);
				}
				hotplaceDto.setFileInfos(fileInfos);
			}
			
			hotplaceService.writeHotplace(hotplaceDto);
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	

	@ApiOperation(value = "", notes = "핫플레이스의 <b>전체 목록</b>을 리턴합니다.")
	@ApiResponses({@ApiResponse(code = 200, message ="핫플 전체 목록 OK"), @ApiResponse(code = 500, message ="서버 에러")})
	@GetMapping
	public ResponseEntity<?> hotplaceList() {
		logger.debug("hotplaceList call");
		try {
			List<HotplaceDto> list = hotplaceService.listHotplace();
			if(list != null && !list.isEmpty()) {
				return new ResponseEntity<List<HotplaceDto>>(list, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
		
	}
	
//	@GetMapping("/view")
//	public String view(@RequestParam("hotplaceno") int hotplaceNo, Model model)
//			throws Exception {
//		HotplaceDto hotplaceDto = hotplaceService.getHotplace(hotplaceNo);
//		model.addAttribute("hotplace", hotplaceDto);
//		return "hotplace/view";
//	}
	@ApiOperation(value = "", notes = "핫플레이스 번호에  <b>해당하는 핫플레이스</b>를 리턴합니다.")
	@ApiResponses({@ApiResponse(code = 200, message ="핫플  OK"), @ApiResponse(code = 500, message ="서버 에러")})
	@GetMapping("/{hotplaceNo}")
	public ResponseEntity<HotplaceDto> getHotplace(@PathVariable("hotplaceNo") @ApiParam(value = "얻어올 핫플레이스 번호.", required = true) int hotplaceNo) throws Exception {
		logger.info("getHotplace - 호출 : " + hotplaceNo);
		return new ResponseEntity<HotplaceDto>(hotplaceService.getHotplace(hotplaceNo), HttpStatus.OK);
	}
	
	@GetMapping("/display/{sfolder}/{ofile}/{sfile}")
	public ResponseEntity<Resource> display(@PathVariable("sfolder") String sfolder, @PathVariable("ofile") String ofile,
			@PathVariable("sfile") String sfile) {
		
		logger.debug("display file info sfolder : {}, ofile : {}, sfile : {}", sfolder, ofile, sfile);
		String file = upload_path + File.separator + sfolder + File.separator + sfile;

		Path filePath = Paths.get(file);
		Resource resource = new FileSystemResource(filePath); // 파일 resource 얻기
		if(!resource.exists()) 
			return new ResponseEntity<Resource>(HttpStatus.NOT_FOUND);
		HttpHeaders headers = new HttpHeaders();
		
		try {
			headers.add("Content-type", Files.probeContentType(filePath));
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Resource>(HttpStatus.CONFLICT);
		}
		return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
	}

	@ApiOperation(value = "", notes = "핫플레이스를 <b>수정</b>합니다.")
	@ApiResponses({@ApiResponse(code = 200, message ="핫플 전체 목록 OK"), @ApiResponse(code = 500, message ="서버 에러")})
	@PutMapping(value = "/{hotplaceNo}")
	public ResponseEntity<?> hotplaceModify(HotplaceDto hotplaceDto,  @RequestPart("thumbNail") List<MultipartFile> files) {
		logger.debug("hotplaceModify hotplaceDto : {}", hotplaceDto);
		
		try {
//			FileUpload 관련 설정.
			System.out.println(hotplaceDto);
			System.out.println(files.get(0).getOriginalFilename());
			logger.debug("MultipartFile.isEmpty : {}", files.get(0).isEmpty());
			if (!files.get(0).isEmpty()) {
//				String realPath = servletContext.getRealPath(upload_path);
//				String realPath = servletContext.getRealPath("/resources/img");
				String today = new SimpleDateFormat("yyMMdd").format(new Date());
				String saveFolder = upload_path + File.separator + today;
				logger.debug("저장 폴더 : {}", saveFolder);
				File folder = new File(saveFolder);
				if (!folder.exists())
					folder.mkdirs();
				List<FileInfoDto> fileInfos = new ArrayList<FileInfoDto>();
				for (MultipartFile mfile : files) {
					FileInfoDto fileInfoDto = new FileInfoDto();
					String originalFileName = mfile.getOriginalFilename();
					if (!originalFileName.isEmpty()) {
						String saveFileName = UUID.randomUUID().toString()
								+ originalFileName.substring(originalFileName.lastIndexOf('.'));
						fileInfoDto.setSaveFolder(today);
						fileInfoDto.setOriginalFile(originalFileName);
						fileInfoDto.setSaveFile(saveFileName);
						logger.debug("원본 파일 이름 : {}, 실제 저장 파일 이름 : {}", mfile.getOriginalFilename(), saveFileName);
						mfile.transferTo(new File(folder, saveFileName));
					}
					fileInfos.add(fileInfoDto);
				}
				hotplaceDto.setFileInfos(fileInfos);
			}
			
			hotplaceService.modifyHotplace(hotplaceDto);
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	

	@ApiOperation(value = "", notes = "핫플레이스를 <b>삭제</b>합니다.")
	@ApiResponses({@ApiResponse(code = 200, message ="핫플 전체 목록 OK"), @ApiResponse(code = 500, message ="서버 에러")})
	@ApiImplicitParams({@ApiImplicitParam(name = "hotplaceNo", value ="삭제 번호", required = true, dataType = "int", paramType = "path")})
	@DeleteMapping(value = "/{hotplaceNo}")
	public ResponseEntity<?> hotplaceDelete(@PathVariable("hotplaceNo") int hotplaceNo) {
		logger.debug("hotplaceDelete hotplaceDto : {}", hotplaceNo);
		try {
			hotplaceService.deleteHotplace(hotplaceNo, upload_path);
//			List<HotplaceDto> list = hotplaceService.listHotplace();
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
//		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
}

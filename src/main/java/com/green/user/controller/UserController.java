package com.green.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.green.menus.dto.MenuDTO;
import com.green.user.dto.UserDTO;
import com.green.user.mapper.UserMapper;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/Users")
public class UserController {
	
	@RequestMapping("/Index")
	public String index() {	
		return "users/index";
	}
//----------------------------------------------------------------	
	@Autowired
	private UserMapper usermapper;
	
	@RequestMapping("/List")
	public String list(Model model) {
		
		List<UserDTO> userlist = usermapper.getUserList();
		model.addAttribute("userlist",userlist);
		return "users/list";
	}
//----------------------------------------------------------------	
	@RequestMapping("/FindForm")
	public String findform(UserDTO userDTO) {
		return "users/find";
	}
	
	@RequestMapping("/Find")
	public String find(UserDTO userDTO, Model model) {
		
		UserDTO result = usermapper.finduser(userDTO);
	    
	    if (result != null) {
	        // "user"라는 이름으로 결과 객체를 담아서 전달
	        model.addAttribute("user", result);
	        return "users/result";
	    } else {
	        model.addAttribute("error", "사용자를 찾을 수 없습니다.");
	        return "users/find";
	    }
	}
//----------------------------------------------------------------	
	@RequestMapping("/FindForm2")
	public String findform2(UserDTO userDTO) {
		return "users/find2";
	}
	
	@RequestMapping("/FindForm3")
	public String findform3(UserDTO userDTO) {
		return "users/find3";
	}
	
	@RequestMapping("/UpdateForm2")
	public String find2(UserDTO userDTO, Model model) {
		UserDTO user = usermapper.finduser(userDTO);
		model.addAttribute("user",user);
		return "users/update2";
	}
	
	@RequestMapping("/Update2")
	public String update2(UserDTO userDTO) {
		usermapper.updateuser(userDTO);
		return "users/index";
	}
//----------------------------------------------------------------	
	@RequestMapping("/LoginForm")
	public String loginform() {
		return "users/login";
	}
	
	@RequestMapping("/Login")
	public String login(UserDTO userDTO, HttpSession session) {
		
		UserDTO result = usermapper.finduser(userDTO);
		Object savedLoc = session.getAttribute("loc");
	    String loc = (savedLoc != null) ? savedLoc.toString() : "/"; // 값이 없으면 메인("/")으로
	    
	    if (result != null && result.getPasswd().equals(userDTO.getPasswd())) {
	    	session.setAttribute("login", result);
	    	session.removeAttribute("loc"); // 로그인 성공 후에는 저장된 loc를 세션에서 지워주는 것이 좋습니다 (재사용 방지)
	        return "redirect:" + loc;
	    } else {
	        return "users/login";
	    }
	}
	
	@RequestMapping("/Logout")
	public String logout(HttpSession session) {
	    session.invalidate(); // Clears everything in the session
	    return "home";
	}

//----------------------------------------------------------------	
	@RequestMapping("/AddForm")
	public String addform() {
		return "users/add";
	}
	
	@RequestMapping("/Add")
	public String add(UserDTO userDTO) {
		usermapper.insertuser(userDTO);
		return "redirect:/Users/List";
	}
//----------------------------------------------------------------		
	@RequestMapping("/UpdateForm")
	public String updateform(UserDTO userDTO, Model model) {
		UserDTO user = usermapper.finduser(userDTO);
		model.addAttribute("user",user);
		return "users/update";
	}

	@RequestMapping("/Update")
	public String update(UserDTO userDTO) {
		usermapper.updateuser(userDTO);
		return "redirect:/Users/List";
	}
//----------------------------------------------------------------		
	@RequestMapping("/Delete")
	public String delete(UserDTO userDTO) {
		usermapper.deleteuser(userDTO);
		return "redirect:/Users/List";
	}
//----------------------------------------------------------------
	// 아이디 중복확인 - 결과문자열을 리턴
	// <b class="green"> 사용가능한 아이디입니다</b>
	// <b class="red"> 사용할 수 없는 아이디입니다</b>
	// Users/IdDupCheck2?userid=sky
	@GetMapping("/IdDupCheck2")
	@ResponseBody // return 되는 글자는 jsp가 아니다
	public UserDTO idCupCheck2(UserDTO userDTO) {
		
		// String userid = userDTO.getUserid(); // 넘어온 userid
		UserDTO user = usermapper.getIdDupCheck(userDTO); // 조회한 userid
		if (user == null) {
			user = new UserDTO();
		}
		
		return user;
	}
//----------------------------------------------------------------	
	@GetMapping("/DupCheckWindow")
	public ModelAndView dupCheckWindow() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("users/idcheck");
		return mv;
	}
//----------------------------------------------------------------		
	@RequestMapping("/DupCheck")
	public ModelAndView dupcheck(UserDTO userDTO) {
		UserDTO user = usermapper.finduser(userDTO);
		String msg = "<b class='red'>사용할 수 없는 아이디 입니다</b>";
		if (user == null) {
			msg = "<b class='green'>사용 가능한 아이디 입니다</b>";
		}
		ModelAndView mv = new ModelAndView();
		mv.setViewName("users/idcheck");
		mv.addObject("msg",msg);
		return mv;
	}
}

/**
 * This file is part of D.A.L.G.S.
 *
 * D.A.L.G.S is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * D.A.L.G.S is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with D.A.L.G.S.  If not, see <http://www.gnu.org/licenses/>.
 */
package es.ucm.fdi.dalgs.web;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import es.ucm.fdi.dalgs.classes.ResultClass;
import es.ucm.fdi.dalgs.course.service.CourseService;
import es.ucm.fdi.dalgs.domain.Course;
import es.ucm.fdi.dalgs.domain.Group;
import es.ucm.fdi.dalgs.domain.User;
import es.ucm.fdi.dalgs.group.service.GroupService;
import es.ucm.fdi.dalgs.user.service.UserService;

@Controller
public class MediatorController {

	private static final Logger logger = LoggerFactory
			.getLogger(MediatorController.class);

	@Autowired
	private UserService serviceUser;

	@Autowired
	private GroupService serviceGroup;

	@Autowired
	private CourseService serviceCourse;

	@RequestMapping(value = "/user.htm")
	public ModelAndView getUserPage(Model model) {
		Map<String, Object> myModel = new HashMap<String, Object>();

		String username = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		User u = serviceUser.findByUsername(username).getSingleElement();
		logger.info(username + "  " + u.getEmail());
		myModel.put("userDetails", u);

		ResultClass<Group> groups = new ResultClass<Group>();

		if (serviceUser.hasRole(u, "ROLE_PROFESSOR")) {
			ResultClass<Course> courses = new ResultClass<Course>();
			courses = serviceCourse.getCourseForCoordinator(u.getId());

			if (!courses.isEmpty()) {
				myModel.put("courses", courses);
			}
			groups = serviceGroup.getGroupsForProfessor(u.getId());

		} else if (serviceUser.hasRole(u, "ROLE_STUDENT")) {
			groups = serviceGroup.getGroupsForStudent(u.getId());

		}
		if (!groups.isEmpty())
			myModel.put("groups", groups);

		return new ModelAndView("user/view", "model", myModel);
	}

	@RequestMapping(value = "/admin.htm")
	public ModelAndView getAdminPage(Model model) {
		Map<String, Object> myModel = new HashMap<String, Object>();

		String username = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		User u = serviceUser.findByUsername(username).getSingleElement();
		logger.info(username + "  " + u.getEmail());
		model.addAttribute("userDetails", u);
		return new ModelAndView("user/admin", "model", myModel);
	}
}
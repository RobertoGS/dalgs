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
package es.ucm.fdi.dalgs.learningGoal.web;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import es.ucm.fdi.dalgs.domain.LearningGoalStatus;
import es.ucm.fdi.dalgs.learningGoal.repository.LearningGoalRepository;

@Component
public class LearningGoalStatusFormatter implements
		Formatter<LearningGoalStatus> {

	@Autowired
	private LearningGoalRepository daoLearningGoal;

	// Some service class which can give the Actor after
	// fetching from Database

	public String print(LearningGoalStatus learningGoalStatus, Locale arg1) {
		return learningGoalStatus.getLearningGoal().getInfo().getName();
	}

	public LearningGoalStatus parse(String learningGoalId, Locale arg1)
			throws ParseException {
		LearningGoalStatus learningGoalStatus = new LearningGoalStatus();
		learningGoalStatus.setLearningGoal(daoLearningGoal
				.getLearningGoalFormatter(Long.parseLong(learningGoalId)));
		return learningGoalStatus;
		// Else you can just return a new object by setting some values
		// which you deem fit.
	}
}

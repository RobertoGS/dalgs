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
package es.ucm.fdi.dalgs.classes;



public class TypeWithEnum {
	
	public enum TypeOfCompetence { General, Transversal, Basica, Especifica };


	private TypeOfCompetence type;
	
	public TypeWithEnum(){
		super();
	}

	public TypeWithEnum(TypeOfCompetence type) {
	    this.type = type;
	}

	public TypeOfCompetence getType() {
	    return type;
	}

	public void setType(TypeOfCompetence type) {
	    this.type = type;
	}
}
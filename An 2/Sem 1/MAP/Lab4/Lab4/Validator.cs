using System;
using System.Collections;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Lab2
{
    class Validator
    {
        private const String kNameEmptyError = "Name can not be empty";
        private const String kDuplicateId = "Id must be unique";
        private const String kInvalidGrade = "Grade is an int between 1 and 10";
    
        private Repository repo;
        private ArrayList errorList;

        /**
         * 
         * Constructor.
         * 
         * @param repo The repository to search in.
         */
        public Validator(Repository repo) {
            this.errorList = new ArrayList();
            this.repo = repo;
        }
    
        /**
         * 
         * Validate a student.
         * 
         * @param student The student to be validated.
         * @return A list of error messages. If list is empty then the student is valid.
         */
        public ArrayList validateStudent(Student student) {
            this.validateId(student.id);
            this.validateName(student.name);
            this.validateGrade(student.grade);
        
            return this.errorList;
        }
    
        /**
         * 
         * Checks if an id is already stored in the repository.
         * 
         * @param id The id to be checked.
         */
        private void validateId(int id) {
            Stack students = this.repo.allStudents();
        
            Boolean found = false;
            while (!students.isEmpty()) {
                Student currStudent = students.pop();
                if (currStudent.id == id) {
                    found = true;
                    break;
                }
            }
        
            if (found) this.errorList.Add(kDuplicateId);
        }
    
        /**
         * 
         * Checks if the student name is an empty string.
         * 
         * @param name The name to be checked.
         */
        private void validateName(String name) {
            if (String.IsNullOrEmpty(name)) {
                this.errorList.Add(kNameEmptyError);
            }
        }
    
        /**
         * 
         * Checks if a grade is between 1 and 10.
         * 
         * @param grade The grade to be checked.
         */
        private void validateGrade(int grade) {
            if (grade > 10 || grade < 0) {
                this.errorList.Add(kInvalidGrade);
            }
        }
    }
}

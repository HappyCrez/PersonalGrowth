Example for formating code\
Classes at PascalCase\
Constructors, Functions and Variables at camalCase

Functions never should return NULL, only some value, check pattern "Special Case";\
All functions must be short (three or maximum 10 strokes);\
Don't process exceptions in code logic (DON'T USE CODE OF ERRORS, USE ONLY UNCHECKED EXCEPTIONS), use try/catch blocks and separate it to new function;\
Don't create excess whitespaces, before/after/in the end of class/function/variable;\
All code format find in book "Clean code" - Martin Fowler;

    public class TaskModule() {
      private InputStream xmlLoader;
      private string mySimpleTitle;
      private string workspaceAbsolutePath;
      private string filename;
    
      public taskModule(string workspaceAbsolutePath, string filename) {
          mySimpleTitle = "Title";
          this.workspaceAbsolutePath = workspaceAbsolutePath;
          this.filename = filename;
          loadWorkspace();
      }
    
      private void loadWorkspace() {
          try {
              openFileStream();
              formatFile();
          }
            catch (NullPointerException e) {
              System.out.println(e);
          }
      }
    
      private void openFileStream() throws NullPointerException {
          xmlLoader = new InputStream();
          /* some code */
          formatFile();
      }
    
      private void formatFile() {
          /* some code */
      }
    }

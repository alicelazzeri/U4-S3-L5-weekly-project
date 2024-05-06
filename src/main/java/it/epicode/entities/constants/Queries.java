package it.epicode.entities.constants;

public class Queries {
    public static class LibraryItems {
        public static final String RESEARCH_BY_ISBN = "RESEARCH_BY_ISBN";
        public static final String RESEARCH_BY_YEAR_OF_PUBLICATION = "RESEARCH_BY_YEAR_OF_PUBLICATION";
        public static final String RESEARCH_BY_TITLE = "RESEARCH_BY_TITLE";
        public static final String REMOVE_BY_ISBN = "REMOVE_BY_ISBN";
    }

    public static class Books {
        public static final String RESEARCH_BY_AUTHOR = "RESEARCH_BY_AUTHOR";
    }

    public static class BookLoans {
        public static final String RESEARCH_BY_EXPIRED_AND_UNRETURNED_LOANS = "RESEARCH_BY_EXPIRED_AND_UNRETURNED_LOANS";
        public static final String RESEARCH_LENT_ITEMS_BY_CARD_NUMBER = "RESEARCH_LENT_ITEMS_BY_CARD_NUMBER";
    }
}

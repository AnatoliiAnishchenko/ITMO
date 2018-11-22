enum TypeOfPerson {
    GoldMiner {
        @Override
        public String toString() {
            return "Gold Miner";
        }
    },
    Picker {
        @Override
        public String toString() {
            return "Picker";
        }
    },
    CommonPerson {
        @Override
        public String toString() {
            return "Common Person";
        }
    }
}

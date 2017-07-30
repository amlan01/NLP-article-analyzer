# NLP-article-analyzer
article analyzer using NLPStanford lib.

goal: whether mention of "x" company was made in an online article
used Boilerpipe lib to extract web feed plus cleaning
POS tagging using Stanford lib basically the model/trainer data set
extracted only proper nouns for accomplishmnet of goal
tokenized / split proper nouns for output from POS tagging --> apple_NNP, microsoft_NNP,...
used hashset and arraylist for final step data capture 

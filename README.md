# NLP-article-analyzer
article analyzer using NLPStanford lib.

Goal: whether mention of "x" company was made in an online article

Used Boilerpipe lib to extract web feed plus cleaning. POS tagging using Stanford lib (basically the model/trainer data set)

Extracted only proper nouns for accomplishmnet of goal. Tokenized / split proper nouns for output from POS tagging --> apple_NNP, microsoft_NNP,... Finally used hashset and arraylist for final step data capture.

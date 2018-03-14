# Makefile for cs 455 PA1
#
# you shouldn't need to change anything in the file unless you
# want to submit additional files.
#
#-----------------------------------------------------------------------
#  Unix commands you can use when this file is in the current directory:
#
#     gmake getfiles
#        Copies and/or links starter files necessary to complete this assignment from 
#	 the course account to the current directory
#
#     gmake submit
#        Submits the assignment.
#
#-----------------------------------------------------------------------
#
# Variable definitions:

HOME = /home/scf-06/csci455
ASSGTS = $(HOME)/assgts
ASSGTDIR = $(ASSGTS)/pa1

#-----------------------------------------------------------------------

getfiles:
	-$(ASSGTS)/bin/safecopy $(ASSGTDIR)/Makefile .
	-$(ASSGTS)/bin/safecopy $(ASSGTDIR)/CoinTossSimulator.java .
	-$(ASSGTS)/bin/safecopy $(ASSGTDIR)/Bar.java .
	-$(ASSGTS)/bin/safecopy $(ASSGTDIR)/CoinSimViewer.list .
	-$(ASSGTS)/bin/safecopy $(ASSGTDIR)/README .

#-----------------------------------------------------------------------
# You will need to change the submit rule if you want to submit
# additional files (unlikely for this assignment).  Note: Do not add
# line-breaks in the middle of the submit command Do not remove the
# leading <tab> in the command given below.

submit:
	submit -user csci455 -tag pa1 README CoinSimViewer.java CoinSimComponent.java CoinTossSimulator.java CoinTossSimulatorTester.java Bar.java



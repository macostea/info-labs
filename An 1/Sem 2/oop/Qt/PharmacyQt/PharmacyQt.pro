#-------------------------------------------------
#
# Project created by QtCreator 2013-05-06T20:48:35
#
#-------------------------------------------------

QT       += core gui

greaterThan(QT_MAJOR_VERSION, 4): QT += widgets

TARGET = PharmacyQt
TEMPLATE = app

LIBS += -stdlib=libc++

QMAKE_CXXFLAGS += -stdlib=libc++
QMAKE_CXXFLAGS += -std=c++11
QMAKE_CXXFLAGS += -mmacosx-version-min=10.7
QMAKE_LFLAGS += -mmacosx-version-min=10.7

SOURCES += main.cpp\
        widget.cpp \

HEADERS  += widget.h \

FORMS    += widget.ui \

macx: LIBS += -L$$PWD/lib/ -lPharmacyController -lPharmacyDomain -lPharmacyRepository -lPharmacyUtilities

INCLUDEPATH += $$PWD/includes
DEPENDPATH += $$PWD/includes

macx: PRE_TARGETDEPS += $$PWD/lib/libPharmacyController.a\
                        $$PWD/lib/libPharmacyDomain.a \
                        $$PWD/lib/libPharmacyRepository.a \
                        $$PWD/lib/libPharmacyUtilities.a

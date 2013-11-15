#include "sort.h"
#include "ui_sort.h"

Sort::Sort(QWidget *parent) :
    QDialog(parent),
    ui(new Ui::Sort)
{
    ui->setupUi(this);
}

Sort::~Sort()
{
    delete ui;
}

void Sort::on_pushButton_clicked()
{
    delete this;
}

#ifndef WIDGET_H
#define WIDGET_H

#include <QWidget>
#include <QListWidgetItem>
#include <QMessageBox>

#include "RepositoryInMemory.h"
#include "Controller.h"
#include "Medication.h"
#include "Sale.h"

namespace Ui {
class Widget;
}

class Widget : public QWidget
{
    Q_OBJECT
    
public:
    explicit Widget(QWidget *parent = 0);
    ~Widget();
    
private slots:
    void itemClicked(QListWidgetItem *item);

    void on_addItem_clicked();

    void on_removeItem_clicked();

    void on_updateItem_clicked();

    void on_pushButton_clicked();

    void on_pushButton_2_clicked();

    void on_getAll_clicked();

    void on_addItem_2_clicked();

    void on_removeItem_2_clicked();

    void on_listWidget_2_itemClicked(QListWidgetItem *item);

    void on_updateSale_clicked();

    void on_top5_clicked();

    void on_getAll_2_clicked();

private:
    Ui::Widget *ui;
    Controller *controller;
    RepositoryAbstract *repo;
    void refreshMedicationListWidget(vector<Medication> list);
    void refreshSaleListWidget(vector<Sale> list);
    void refreshIds(vector<Medication> list);
    void refreshSaleIds(vector<Sale> list);
    QList<int> medicationIds;
    QList<int> saleIds;
    vector<Sale> top5;
};

#endif // WIDGET_H

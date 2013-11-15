#include "widget.h"
#include "sort.h"
#include "ui_widget.h"


void addTestValues(Controller* controller)
{
    controller->addNewMedication("lol", 20, 20);
    controller->addNewMedication("yum", 30, 450);
    controller->addNewMedication("ggg", 50, 1000);
    controller->addNewMedication("lo21", 50, 90);

}

Widget::Widget(QWidget *parent) :
    QWidget(parent),
    ui(new Ui::Widget)
{
    ui->setupUi(this);

    ui->tabWidget->widget(0)->setLayout(ui->verticalLayout_3);
    ui->tabWidget->widget(1)->setLayout(ui->verticalLayout_7);
    ui->tabWidget->setCurrentIndex(0);

    RepositoryAbstract *repository = new RepositoryInMemory();
    Controller *controller = new Controller(repository);
    this->controller = controller;
    this->repo = repository;
    addTestValues(controller);

    ui->sortBy->addItem("Name");
    ui->sortBy->addItem("Quantity");
    ui->sortDirection->addItem("Ascending");
    ui->sortDirection->addItem("Descending");

    vector<Medication> result = this->controller->listMedication();
    this->refreshMedicationListWidget(result);
    this->refreshIds(result);

    connect(ui->listWidget, SIGNAL(itemClicked(QListWidgetItem*)), this, SLOT(itemClicked(QListWidgetItem*)));

}

Widget::~Widget()
{
    delete ui;
    delete controller;
}

void Widget::refreshMedicationListWidget(vector<Medication> list)
{
    ui->listWidget->clear();
    for (int i=0; i<list.size(); i++){
        ui->listWidget->addItem(QString::fromStdString(list[i].getName()));
    }
}

void Widget::refreshSaleListWidget(vector<Sale> list)
{
    ui->listWidget_2->clear();
    for (int i=0; i<list.size(); i++){
        ui->listWidget_2->addItem(QString::fromStdString(list[i].getMedication()->getName()));
    }
}

void Widget::refreshIds(vector<Medication> list)
{
    this->medicationIds.clear();
    for (int i=0; i<list.size(); i++){
        this->medicationIds.append(list[i].getId());
    }
}

void Widget::itemClicked(QListWidgetItem *item)
{
    int index = ui->listWidget->row(item);
    ui->medName->setText(item->text());
    Medication *selectedMedication = this->repo->getMedicationForId(this->medicationIds[index]);
    ui->medConcentration->setText(QString::fromStdString(to_string(selectedMedication->getConcentration())));
    ui->medQuantity->setText(QString::fromStdString(to_string(selectedMedication->getQuantity())));

}

void Widget::on_addItem_clicked()
{
    this->controller->addNewMedication("New Medication", 1, 1);
    ui->listWidget->addItem("New Medication");
    vector<Medication> allMeds = this->controller->listMedication();
    this->refreshIds(allMeds);
}

void Widget::on_removeItem_clicked()
{
    QList<QListWidgetItem*> selectedItems = ui->listWidget->selectedItems();
    if (selectedItems.size() != 0){
        int index = ui->listWidget->row(selectedItems[0]);
        this->controller->deleteMedication(this->medicationIds[index]);

        delete ui->listWidget->selectedItems()[0];
        vector<Medication> allMeds = this->controller->listMedication();
        this->refreshIds(allMeds);
    }

}

void Widget::on_updateItem_clicked()
{
    string name = ui->medName->text().toStdString();
    int concentration = ui->medConcentration->text().toUInt();
    int quantity = ui->medQuantity->text().toUInt();

    int index = ui->listWidget->row(ui->listWidget->selectedItems()[0]);
    this->controller->updateMedication(this->medicationIds[index], name, concentration, quantity);

    vector<Medication> result = this->controller->listMedication();
    this->refreshMedicationListWidget(result);
    this->refreshIds(result);
}

void Widget::on_pushButton_clicked()
{
    QString sortByText = ui->sortBy->currentText();
    QString sortDirectionText = ui->sortDirection->currentText();

    int sortBy, sortDirection;

    if (sortByText == "Name"){
        sortBy = SORT_BY_NAME;
    }
    if (sortByText == "Quantity"){
        sortBy = SORT_BY_QUANTITY;
    }
    if (sortDirectionText == "Ascending"){
        sortDirection = SORT_DIRECTION_ASC;
    }
    if (sortDirectionText == "Descending"){
        sortDirection = SORT_DIRECTION_DSC;
    }
    vector<Medication> result = this->controller->sort(sortBy, sortDirection);
    this->refreshMedicationListWidget(result);
    this->refreshIds(result);

}

void Widget::on_pushButton_2_clicked()
{
    int quantity = ui->maxQuantity->text().toInt();
    vector<Medication> result = this->controller->filter(FILTER_BY_QUANTITY_LESS_THAN, quantity);

    this->refreshMedicationListWidget(result);
    this->refreshIds(result);
}

void Widget::on_getAll_clicked()
{
    vector<Medication> result = this->controller->listMedication();

    this->refreshMedicationListWidget(result);
    this->refreshIds(result);
}

void Widget::refreshSaleIds(vector<Sale> list)
{
    this->saleIds.clear();
    for (int i=0; i<list.size(); i++){
        this->saleIds.append(list[i].getId());
    }
}

void Widget::on_addItem_2_clicked()
{
    this->controller->addNewSale(-1,1,1);
    ui->listWidget_2->addItem("New Sale");
    vector<Sale> allSales = this->controller->listSales();
    this->refreshSaleIds(allSales);
}

void Widget::on_removeItem_2_clicked()
{
    QList<QListWidgetItem*> selectedItems = ui->listWidget_2->selectedItems();
    if (selectedItems.size() != 0){
        int index = ui->listWidget_2->row(selectedItems[0]);
        this->controller->deleteSale(this->saleIds[index]);

        delete ui->listWidget_2->selectedItems()[0];
        vector<Sale> allSales = this->controller->listSales();
        this->refreshSaleIds(allSales);
    }

}

void Widget::on_listWidget_2_itemClicked(QListWidgetItem *item)
{
    int index = ui->listWidget_2->row(item);
    if (this->top5.empty()){
        Sale *selectedSale = this->controller->getSaleForId(this->saleIds[index]);
        if (selectedSale->getMedication() == NULL){
            ui->medName_2->setText("Not Set");
            ui->saleDate->clear();
            ui->saleQuantity->clear();
        } else {
            ui->medName_2->setText(QString::fromStdString(selectedSale->getMedication()->getName()));
            ui->saleDate->setValue(selectedSale->getDate());
            ui->saleQuantity->setText(QString::fromStdString(to_string(selectedSale->getQuantity())));
        }
    } else {
        ui->medName_2->setText(QString::fromStdString(this->top5[index].getMedication()->getName()));
        ui->saleQuantity->setText(QString::fromStdString(to_string(this->top5[index].getQuantity())));
    }

}

void Widget::on_updateSale_clicked()
{
    string name = ui->medName_2->text().toStdString();
    vector<Medication> allMeds = this->controller->listMedication();
    int id=-1;
    for (int i=0; i<allMeds.size(); i++){
        if (allMeds[i].getName() == name){
            id = allMeds[i].getId();
            break;
        }
    }
    if (id == -1){
        QMessageBox *mb = new QMessageBox(this);
        mb->addButton(QMessageBox::Ok);
        mb->setIcon(QMessageBox::Critical);
        mb->setWindowModality(Qt::WindowModal);
        mb->setText("Medication not found!");
        mb->exec();
        return;
    } else {
        int date = ui->saleDate->text().toUInt();
        int quantity = ui->saleQuantity->text().toUInt();
        int index = ui->listWidget_2->row(ui->listWidget_2->selectedItems()[0]);
        if (this->controller->getSaleForId(this->saleIds[index])->getMedication() == NULL){
            Medication *med = this->controller->getMedicationForId(id);
            this->controller->deleteSale(this->saleIds[index]);
            this->controller->addNewSale(med->getId(), date, quantity);
        } else {
            this->controller->updateSale(this->saleIds[index], date, quantity);
        }
    }

    vector<Sale> result = this->controller->listSales();
    this->refreshSaleListWidget(result);
    this->refreshSaleIds(result);
}


void Widget::on_top5_clicked()
{
    vector<Sale> result = this->controller->getTopFive(ui->startDate->value(), ui->endDate->value());
    this->top5 = result;
    this->refreshSaleListWidget(result);
    this->refreshSaleIds(result);

// ////////////////////////////////////

    ui->addItem_2->setEnabled(false);
    ui->removeItem_2->setEnabled(false);
    ui->top5->setEnabled(false);
    ui->updateSale->setEnabled(false);
    ui->saleDate->setEnabled(false);
}

void Widget::on_getAll_2_clicked()
{
    vector<Sale> result = this->controller->listSales();
    this->refreshSaleListWidget(result);
    this->refreshSaleIds(result);

    // ////////////////////////////////////

    this->top5.clear();

    ui->addItem_2->setEnabled(true);
    ui->removeItem_2->setEnabled(true);
    ui->top5->setEnabled(true);
    ui->updateSale->setEnabled(true);
    ui->saleDate->setEnabled(true);
}

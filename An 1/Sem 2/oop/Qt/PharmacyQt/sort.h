#ifndef SORT_H
#define SORT_H

#include <QDialog>

namespace Ui {
class Sort;
}

class Sort : public QDialog
{
    Q_OBJECT
    
public:
    explicit Sort(QWidget *parent = 0);
    ~Sort();
    
private slots:
    void on_pushButton_clicked();

private:
    Ui::Sort *ui;
};

#endif // SORT_H

 #language: ru
 Функционал: Страхование

   Сценарий: Страхование путешественников, неполное заполнение формы, успешный
     Когда Нажать на – Страхование
     И Находим блок с названием:"Страхование для путешественников"
     Тогда Нажать на – 'Оформить Онлайн'

     Когда Выбор полиса"Спортивный"
     Тогда Нажать на – 'Оформить'

     Когда Заполняем общие данные застрахованных
       | Фамилия       | Иванов     |
       | Имя           | Иван       |
       | Дата рождения | 01.01.1990 |

     И Заполняем общие данные страхователя
       | Фамилия       | Петров     |
       | Имя           | Иван       |
       | Отчество      | Иванович   |
       | Дата рождения | 01.01.1990 |

     И Заполняем данные паспорта страхователя
       | Серия       | 4515       |
       | Номер       | 758545     |
       | Дата выдачи | 01.01.2010 |
       | Кем выдан   | Кем выдан  |

     Тогда Проверяем заполненные общие данные застрахованных
       | Фамилия       | Иванов     |
       | Имя           | Иван       |
       | Дата рождения | 01.01.1990 |

     И Проверяем заполненные общие данные страхователя
       | Фамилия       | Петров     |
       | Имя           | Иван       |
       | Отчество      | Иванович   |
       | Дата рождения | 01.01.1990 |

     И Проверяем заполненные данные паспорта страхователя
       | Серия       | 4515       |
       | Номер       | 758545     |
       | Дата выдачи | 01.01.2010 |
       | Кем выдан   | Кем выдан  |

     Когда Нажать на – Продолжить
     Тогда Появилось сообщение - Заполнены не все обязательные поля"3"

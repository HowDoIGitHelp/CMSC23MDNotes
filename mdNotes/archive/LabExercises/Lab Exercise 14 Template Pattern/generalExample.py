from abc import ABC, abstractmethod

class Template(ABC):
    @abstractmethod
    def step1(self): #this has to be overridden
        pass

    def step2(self): #this has a default behavior but can be overridden
        print("step 2: do something by default (t)")

    def step3(self): #this has a default behavior but can be overridden
        print("step 3: do something by default (t)")

    @abstractmethod
    def step4(self): #this has to be overridden
        pass

    def templateMethod(self):
        self.step1()
        for i in range(3):
            self.step2()
        self.step3()
        self.step4()

class Specialization1(Template):
    def step1(self):
        print("step 1: do something (s1)")

    def step4(self):
        print("step 4: do something (s1)")

class Specialization2(Template):
    def step1(self):
        print("step 1: do something else (s2)")

    def step2(self):
        print("step 2: do something special (s2)")

    def step4(self):
        print("step 4: do something else (s2)")

s1:Template = Specialization1()
s2:Template = Specialization2()

s1.templateMethod()
print()
print()
s2.templateMethod()

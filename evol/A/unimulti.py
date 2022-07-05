import re
import sys


# noinspection SpellCheckingInspection
class Task:
    f_mem = {}

    @staticmethod
    def make_eatable_for_eval(f):
        f = re.sub(r'([0-9y])(x)', r'\1*x', f)
        f = re.sub(r'([0-9x])(y)', r'\1*y', f)
        f = re.sub(r'([\^])', r'**', f)
        return f

    @staticmethod
    def f(f, p):
        if p in Task.f_mem.keys():
            return Task.f_mem[p]

        value = eval(f, {'x': p[0], 'y': p[1]})
        Task.f_mem[p] = value
        return value

    @staticmethod
    def defined(p):
        x, y = p
        return abs(x) <= 10 and abs(y) <= 10

    @staticmethod
    def do(f, p, points, cmp_op, not_def_value):
        res = []
        for point in points:
            if not Task.defined(point):
                res.append(not_def_value)
                continue
            res.append(cmp_op(Task.f(f, p), Task.f(f, point)))

        return res

    @staticmethod
    def analyze_point(f, p):
        points = [
            (p[0] - 1, p[1]),
            (p[0] + 1, p[1]),
            (p[0], p[1] - 1),
            (p[0], p[1] + 1)
        ]

        res_minima = Task.do(f, p, points, lambda v1, v2: v1 < v2, True)
        res_maxima = Task.do(f, p, points, lambda v1, v2: v1 > v2, True)
        res_plateau = Task.do(f, p, points, lambda v1, v2: v1 == v2, False)
        return all(x == True for x in res_minima), \
               all(x == True for x in res_maxima), \
               any(x == True for x in res_plateau)

    @staticmethod
    def solve(f):
        f = Task.make_eatable_for_eval(f)

        minimas, maximas, plateaus = 0, 0, 0
        for x in range(-10, 11):
            for y in range(-10, 11):
                minima, maxima, plateau = Task.analyze_point(f, (x, y))

                minimas += minima
                maximas += maxima
                plateaus += plateau

        minimas = ('No', 'Yes')[minimas > 1]
        maximas = ('No', 'Yes')[maximas > 1]
        plateaus = ('No', 'Yes')[plateaus > 1]

        return maximas, minimas, plateaus

    @staticmethod
    def main():
        def get_filenames():
            task_name = sys.argv[0].split('.')[0]
            input = f'{task_name}.in'
            output = f'{task_name}.out'
            return input, output

        input_file, output_file = get_filenames()

        with open(input_file) as f:
            polynom = f.readline().strip()

        maximas, minimas, plateaus = Task.solve(polynom)

        with open(output_file, 'w') as f:
            f.write(f'Multiple local maxima: {maximas}\n')
            f.write(f'Multiple local minima: {minimas}\n')
            f.write(f'Plateaus: {plateaus}')


if __name__ == '__main__':
    Task.main()

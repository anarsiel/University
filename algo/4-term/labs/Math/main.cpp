//
// Created by Anarsiel on 15/06/2020.
//

#include "primitive.h"

int main() {
    // 7.1.16
    std::cout << primitive::kth_prime::compute({0}) << std::endl;
    std::cout << primitive::kth_prime::compute({1}) << std::endl;
    std::cout << primitive::kth_prime::compute({2}) << std::endl;
    std::cout << primitive::kth_prime::compute({3}) << std::endl;
    std::cout << primitive::kth_prime::compute({4}) << std::endl;
    std::cout << primitive::kth_prime::compute({5}) << std::endl;
    return 0;
}
